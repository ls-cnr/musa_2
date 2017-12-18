// Agent conf_manager in project musa_2

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start 
: 
	true 
<- 
	.print("hello world.");
	!simulate_graph_generation;
.


+!simulate_graph_generation
:
	true
<-
	SpecId="spec01";
	
	.concat("CM_", SpecId, CMArtifactName);
	
	makeArtifact(CMArtifactName,"selfconf.ConfigSelector",[SpecId],AccessManagerId);
	
	/* Simulate a WTS creation */
	
	/*W0 -> A */
	S1 = enode(w([w0(w0)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]) , 0, "normal");
	D1 = enode(w([a(a)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	M1 = map([D1], ["S1"]);
	notifyENode(expansionNode(S1, "CAP", 0, M1, "agent1"));
	
	/*W0 -> B */
	S2 = enode(w([w0(w0)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	D2 = enode(w([b(b)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	M2 = map([D2], ["S2"]);
	notifyENode(expansionNode(S2, "CAP", 0, M2, "agent1"));
	
	/*A -> C */
	S3 = enode(w([a(a)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	D3 = enode(w([c(c)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	M3 = map([D3], ["S3"]);
	notifyENode(expansionNode(S3, "CAP", 0, M3, "agent1"));
	
	/*B -> C */
	S4 = enode(w([b(b)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	D4 = enode(w([c(c)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	M4 = map([D4], ["S4"]);
	notifyENode(expansionNode(S4, "CAP", 0, M4, "agent1"));
	
	/* B -> X(B), X(B) -> 1, X(B) -> 2, SOL(1), SOL(2) */
	S5 = enode(w([b(b)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "normal");
	D5_1 = enode(w([b1(b1)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "is_exit");
	D5_2 = enode(w([b2(b2)]), tokensconfig([c("Formula0",["Start"])],[s("Formula0","W")]), 0, "is_exit");
	M5 = map([D5_1, D5_2], ["S5_1", "S5_2"]);
	notifyENode(expansionNode(S5, "check_storehouse(order)", 0, M5, "agent1"));
	
	/* End of toy model */

.



{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization  
//{ include("$jacamoJar/templates/org-obedient.asl") }
