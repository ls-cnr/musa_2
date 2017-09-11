/* OBS+ Service Compositor agent */

{ include("inc/common_negotiation.asl") }

{ begin namespace(priv, local) }

    ~init.
    numberOfServices(0).
    round(1).
    stop(0).

    requested_services_initialised
    :-
        numberOfServices(NR) &
        .findall(I, initialised(I, _), I_list) &
        .length(I_list, NI) &
        NR = NI. // requested services = initialised ones

    valid_round :- round(T) & deadline(Tmax) & T <= Tmax.

    +!create_services([service(Name, URI)|R])
    :
        numberOfServices(N) & attributes(QoS) & deadline(T)
    <-
        .concat(Name, "_AS", ServiceId);
        .create_agent(ServiceId, "abstract_service.asl");
        .send(ServiceId, achieve, initialise(Name, URI, QoS, T));
        -+numberOfServices(N+1);
        !create_services(R).

    +!create_services([]).

{ end }

bestOffersPackage([]).
agreement(0).
paretoOptimalAgreement(0).

@p1
+!start(Services, QoS, C, T) : priv::~init
<-
    if (.length(Services, Slength) & Slength = 1) {
        .print("*** no need to negotiate on 1 service, aborting"); .fail;
    };

    +priv::attributes(QoS); +priv::constraints(C); +priv::deadline(T);

    // get debug mode flag
    ?::debugMode(DM); +priv::debugMode(DM);

    // create environment artifact for checking negotiation status
    ?::currentEnv(Env);
    .concat(Env, ".ServiceCompositor", EnvArtifact);
    .my_name(Me); .concat(Me, "_env", EnvName);
    makeArtifact(EnvName, EnvArtifact, [QoS, C, T]);

    // create organisation
    makeArtifact(negotiation_org, "ora4mas.nopl.OrgBoard", ["src/org/negotiation.xml"], OrgArtId);
    focus(OrgArtId);

    // create main group
    .my_name(Me);
    createGroup(negotiation_group, negotiationGroup, GrArtId);
    setOwner(Me);
    if (DM = "enabled") {
        debug(inspector_gui(on))[artifact_id(GrArtId)];
    };
    adoptRole(service_compositor)[artifact_id(GrArtId)];
    focus(GrArtId);

    .print("*** initialising MAS");

    // create services
    !priv::create_services(Services);
    .wait(priv::requested_services_initialised, 5 * 1000);
    .findall(X, ::play(X, abstract_service, _), AS_list);
    +priv::services(AS_list);

    // create scheme
    createScheme(statusscheme, doSequenceStep, SchArtId);
    ?debugMode(DM);
    if (DM = "enabled") {
        debug(inspector_gui(on))[artifact_id(SchArtId)];
    };
    focus(SchArtId);
    .my_name(Me); setOwner(Me)[artifact_id(SchArtId)];
    addScheme(statusscheme)[artifact_id(GrArtId)];
    .broadcast(tell, commitTo(statusscheme));

    .print("waiting for obligation commitments");
    while (priv::~init) {
        .wait(500);
        .findall(N, initialised(_, N), Nlist);
        SPinit = math.sum(Nlist);
        ?priv::numberOfServices(ASinit);
        .count(::commitment(_, mServiceProvider, statusscheme), SPc);
        .count(::commitment(_, mAbstractService, statusscheme), ASc);
        if (SPinit + ASinit = SPc + ASc) {
            -priv::~init; +priv::init;
            .print("*** initialisation done, starting negotiation");
            .wait(1000); // just to be safe
            !negotiate;
        };
    }.

-!start(_,_,_,_)[error(E), error_msg(M), code_line(L)]
<-
    .print("*** error ", E, " at line ", L, ": ", M).

@pSCorgPlan[atomic]
+!check_status[source(self)]
:
    .count(default::standingOffers(_)[source(_)], ALN) &
    priv::numberOfServices(N) & ALN = N & priv::currentAS(AS)
<-
    .findall(offers(A, L), default::standingOffers(L)[source(A)], AL);
    .term2string(AL, ALS);
    currentStatus(ALS, BestOffersPkg, RemainingIssues, Agr, ParetoOptAgr, StopCond);

    -+::bestOffersPackage(BestOffersPkg);
    -+::agreement(Agr);
    -+::paretoOptimalAgreement(ParetoOptAgr);
    -+priv::remainingIssues(RemainingIssues);
    -+priv::stop(StopCond);

    // eventually make current AS adjust its offers
    if (StopCond = 0 & ParetoOptAgr = 0) {
        .abolish(adjusted[source(AS)]);
        calculateRefPoint(AS, RefPoint);
        .send(AS, achieve, adjust_offers(RefPoint));
        .wait(adjusted[source(AS)], 10 * 1000);
    }.

-!check_status[source(self)] <- .wait(500); !check_status.

+::goalState(statusscheme, check_status, _, _, satisfied) <- +priv::statusChecked.

@p2
+!negotiate[source(self)]
:
    priv::init & priv::valid_round &
    priv::stop(St) &
    ::paretoOptimalAgreement(P) &
    St = 0 & P = 0 &
    priv::round(T) & priv::services(L)
<-
    .print("*** round ", T);
    for (.member(AS, L)) {
    	?::paretoOptimalAgreement(Ag);
    	if (Ag = 0) {
			// reset round status first
			-priv::statusChecked;
			.abolish(default::standingOffers(_)[source(_)]);
			resetGoal(check_status);
			.wait(::goalState(statusscheme, check_status, _, _, E) & E = enabled, 5 * 1000);

			.print("elaborating ", AS); -+priv::currentAS(AS);

			/* 1. get all offers from AS-SP
			 * 2. define best offers package
			 * 3. check negotiation results
			 * 4. eventually make AS recheck_status its offers
			 */
			commitMission(mServiceCompositor);

			.wait(priv::statusChecked, 5 * 1000);
        };
    };

    -+priv::round(T+1);
    !negotiate.

@p2GoodEnding
+!negotiate[source(self)]
:
    priv::init & ::paretoOptimalAgreement(P) & P = 1
<-
    +::done;
    .print("*** negotiation succeeded").

@p2Stopped
+!negotiate[source(self)]
:
    priv::init & priv::stop(St) & St = 1
<-
    +::done;
    .print("*** stopping condition met").

@p2BadEnding
+!negotiate[source(self)]
:
    priv::init & not priv::valid_round & ::agreement(A)
<-
    +::done;
    if (A = 1) {
        .print("*** negotiation deadline met: reached a non Pareto-optimal agreement");
    } else {
        .print("*** negotiation failed: no (near) Pareto-optimal agreement reached");
    }.


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
{ include("$jacamoJar/templates/org-obedient.asl") }
