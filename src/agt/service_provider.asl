/* OBS+ Service Provider agent */

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
{ include("$jacamoJar/templates/org-obedient.asl") }

{ begin namespace(priv, local) }

    ~init.

{ end }

!initialise.

+!initialise : priv::~init
<-
    ?service(Name);

    .concat(Name, "_wsp", W);
    +priv::workspace(W);

    .concat(Name, "_subgroup", G);
    +priv::subgroup(G);

    !join.

+!join[source(self)]
:
    priv::subgroup(G) & priv::workspace(W)
<-
    lookupArtifact(G, GrArtId);
    adoptRole(service_provider)[artifact_id(GrArtId)];
    focus(GrArtId);
    joinWorkspace(W, WspId);
    providerInitialised;
    -priv::~init.

-!join[source(self)]
:
    priv::subgroup(G) & not focused(_, G, _)
<-
    .wait(500);
    !join.

+commitTo(scheme, SchName)[source(A)]
:
    sc(S) & A = S
<-
    +priv::commitment(A, mServiceProvider, SchName);
    commitMission(mServiceProvider)[artifact_name(SchName)].

+!send_offer[source(self)]
<-
    .my_name(Me); ?offer(O); .term2string(O, Offer);
    sendOffer(Me, Offer);
    ?as(S); .send(S, tell, offerSent).

+!adjust_offer(RefPoint)[source(A)]
:
    as(S) & A = S
<-
    .term2string(RefPoint, RefPointStr);
    ?id(Id); .term2string(Id, IdStr);
    ?parameters(Par); .term2string(Par, ParStr);
    adjustOffer(RefPointStr, IdStr, ParStr, NewOffer);
    -+offer(NewOffer);
    .my_name(Me);
    .send(A, tell, adjusted(Me)).
