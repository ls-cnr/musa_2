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

	/* Case study */	
//	/*W0 -> A */
//	S1 = enode(w([w0(w0)]), [], 0, "normal");
//	D1 = enode(w([a(a)]), [], 0, "normal");
//	M1 = map([D1], ["S1"]);
//	notifyENode(expansionNode(S1, "request(order)", 0, M1, "agent1"));
//	
//	/* A -> X(A), X(A) -> B, X(A) -> E */
//	S2 = enode(w([a(a)]), [], 0, "normal");
//	D2_1 = enode(w([b(b)]), [], 0, "normal");
//	D2_2 = enode(w([e(e)]), [], 0, "normal");
//	M2 = map([D2_1, D2_2], ["S2_1", "S2_2"]);
//	notifyENode(expansionNode(S2, "check_user(order, user, data)", 0, M2, "agent1"));
//	
//	/* B -> C */
//	S3 = enode(w([b(b)]), [], 0, "normal");
//	D3 = enode(w([c(c)]), [], 0, "normal");
//	M3 = map([D3], ["S3"]);
//	notifyENode(expansionNode(S3, "send_reg_form(user)", 0, M3, "agent1"));
//	
//	/* B -> E (potrebbe essere un refuso)*/
//	S3_ = enode(w([b(b)]), [], 0, "normal");
//	D3_ = enode(w([e(e)]), [], 0, "normal");
//	M3_ = map([D3_], ["S3_"]);
//	notifyENode(expansionNode(S3_, "capability", 0, M3_, "agent1"));
//	
//	/* C -> D */
//	S4 = enode(w([c(c)]), [], 0, "normal");
//	D4 = enode(w([d(d)]), [], 0, "normal");
//	M4 = map([D4], ["S4"]);
//	notifyENode(expansionNode(S4, "user_data(user)", 0, M4, "agent1"));
//	
//	/* D -> X(D), X(D) -> B, X(D) -> E */
//	S5 = enode(w([d(d)]), [], 0, "normal");
//	D5_1 = enode(w([b(b)]), [], 0, "normal");
//	D5_2 = enode(w([e(e)]), [], 0, "normal");
//	M5 = map([D5_1, D5_2], ["S51", "S52"]);
//	notifyENode(expansionNode(S5, "add_user(user)", 0, M5, "agent1"));
//	
//	/* E -> X(E), X(E) -> G, X(E) -> F */
//	S6 = enode(w([e(e)]), [], 0, "normal");
//	D6_1 = enode(w([g(g)]), [], 0, "normal");
//	D6_2 = enode(w([f(f)]), [], 0, "normal");
//	M6 = map([D6_1, D6_2], ["S6_1", "S6_2"]);
//	notifyENode(expansionNode(S6, "check_storehouse(order)", 0, M6, "agent1"));
//	
//	/* F -> I */
//	S7 = enode(w([f(f)]), [], 0, "normal");
//	D7 = enode(w([i(i)]), [], 0, "normal");
//	M7 = map([D7], ["S7"]);
//	notifyENode(expansionNode(S7, "generate_invoice(order,invoice)", 0, M7, "agent1"));
//	
//	/* I -> L */
//	S8 = enode(w([i(i)]), [], 0, "normal");
//	D8 = enode(w([l(l)]), [], 0, "normal");
//	M8 = map([D8], ["S8"]);
//	notifyENode(expansionNode(S8, "upload_on_cloud(invoice,system_space)", 0, M8, "agent1"));
//	
//	/* I -> M */
//	S9 = enode(w([i(i)]), [], 0, "normal");
//	D9 = enode(w([m(m)]), [], 0, "normal");
//	M9 = map([D9], ["S9"]);
//	notifyENode(expansionNode(S9, "upload_on_cloud(invoice,email) ", 0, M9, "agent1"));
//	
//	/* L -> M */
//	S10 = enode(w([l(l)]), [], 0, "normal");
//	D10 = enode(w([m(m)]), [], 0, "normal");
//	M10 = map([D10], ["S10"]);
//	notifyENode(expansionNode(S10, "share_link_file(rx_file,email)", 0, M10, "agent1"));
//	
//	/* M -> N, SOL(N) */
//	S11 = enode(w([m(m)]), [], 0, "normal");
//	D11 = enode(w([n(n)]), [], 0, "is_exit");
//	M11 = map([D11], ["S11"]);
//	notifyENode(expansionNode(S11, "notify_storehouse_manager(order)", 0, M11, "agent1"));
//	
//	/* G -> H, SOL(H) */
//	S12 = enode(w([g(g)]), [], 0, "normal");
//	D12 = enode(w([h(h)]), [], 0, "is_exit");
//	M12 = map([D12], ["S12"]);
//	notifyENode(expansionNode(S12, "notify_stock_failure(order)", 0, M12, "agent1"));
//	getSolution;
//	/* End of case study */
	
	/* Toy Model */
	
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
