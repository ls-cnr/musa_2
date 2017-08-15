/* OBS+ Abstract Service agent */

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
{ include("$jacamoJar/templates/org-obedient.asl") }

{ include("inc/common_negotiation.asl") }

{ begin namespace(priv, local) }

    ~init.

    +!create_env(Name, URI, QoS, T)
    <-
        ?default::currentEnv(Env);
        .concat(Env, ".AbstractService", EnvArtifact);
        .concat(Name, "_env", EnvName);
        .concat(Name, "_wsp", WspName);

        createWorkspace(WspName);
        joinWorkspace(WspName, WpsId);

        .my_name(Me);
        makeArtifact(EnvName, EnvArtifact, [Me, Name, URI, T]);

        ?sc(S); createProviders(S).

    +!adjust([SP|L], RefPoint)
    <-
        .send(SP, achieve, adjust_offer(RefPoint));
        !adjust(L, RefPoint).

    +!adjust([], _) : sc(S) & numberOfSP(N)
    <-
        while (.count(adjusted(_), Ad) & Ad \== N) {
            .wait(250);
        };
        .send(S, tell, adjusted).

{ end }

+!initialise(Name, URI, QoS, T)[source(A)] : priv::~init
<-
    +priv::sc(A); +priv::attributes(QoS); +priv::deadline(T);

    !join_negotiation_group;

    // create service subgroup
    .concat(Name, "_subgroup", G);
    createGroup(G, serviceGroup, GrArtId);
    setParentGroup(negotiation_group)[artifact_id(GrArtId)];
    ?::debugMode(DM);
    if (DM = "enabled") {
        debug(inspector_gui(on))[artifact_id(GrArtId)];
    };
    focus(GrArtId);

    // create SPs
    !priv::create_env(Name, URI, QoS, T);

    getNumberOfSP(N);

    if (N < 1) {
        .print("zero SPs found, aborting");
        .fail;
    } else {
        +priv::numberOfSP(N);
    };

    .my_name(Me); .send(A, tell, initialised(Me, N));

    -priv::~init.

-!initialise(_,_,_,_)[error(E), error_msg(M), code_line(L)]
<-
    .print("*** error ", E, " at line ", L, " while initialising: ", M).

+!join_negotiation_group[source(self)]
<-
    lookupArtifact(negotiation_group, GrArtId);
    adoptRole(abstract_service)[artifact_id(GrArtId)];
    focus(GrArtId).

-!join_negotiation_group[source(self)]
:
    not focused(_, negotiation_group, _)
<-
    .wait(500);
    !join_negotiation_group.

+!send_standing_offers
:
    priv::commitment(A, _, _) & priv::sc(S) & A = S &
    .count(default::offerSent[source(_)], Sent) & Sent = N
<-
    .abolish(default::offerSent[source(_)]);
    getStandingOffers(L);
    .send(A, tell, standingOffers(L)).

-!send_standing_offers <- .wait(500); !send_standing_offers.

+!adjust_offers(RefPoint)[source(A)]
:
    priv::attributes(QoS) & priv::sc(S) & A = S
<-
    if (.list(RefPoint) & .length(RefPoint, L) & .length(QoS, M) & L = M) {
        .print("adjusting offers");
        .abolish(default::adjusted(_));
        .findall(SP, ::play(SP, service_provider, _), SPlist);
        !priv::adjust(SPlist, RefPoint);
    } else {
        .print("*** error: invalid reference point, can't adjust");
        .fail;
    }.

+commitTo(scheme, SchName)[source(A)]
:
    priv::sc(S) & A = S
<-
    +priv::commitment(A, mAbstractService, SchName);
    commitMission(mAbstractService)[artifact_name(SchName)].
