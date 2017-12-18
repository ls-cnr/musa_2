// Agent specification_manager in project musa_2
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
// uncomment the include below to have a agent that always complies with its organization  
//{ include("$jacamoJar/templates/org-obedient.asl") }


/* Initial beliefs and rules */
iterazioni(0).
num_nodi(0).

/* TODO: aggiungere 
 * goals e domain assumptions
 * sotto forma di beliefs
 */
!start.

/* Initial goals */



/* Plans */
+!start
<-
	.println("HELLO");
	!new_injection("spec01");
.

+!new_injection(SpecId) 
: 
	true 
<- 
	?joined(my_spec_wp,LocalWp);
	//cartago.set_current_wsp(LocalWp);
	
	.concat("SG_", SpecId, SGArtifactName);
	.concat("AM_", SpecId, AMArtifactName);

	makeArtifact(AMArtifactName,"selfconf.AccessManagerArtifact",[SpecId],AccessManagerId)[wsp(my_spec_wp)];//[artifact_id(ArtId)];//[wsp_id("node_local")];
	makeArtifact(SGArtifactName,"selfconf.SolutionGraphArtifact",[],SolutionGraphId)[wsp(my_spec_wp)];//[artifact_id(ArtId)];//[wsp_id("node_local")];	
	
	.wait(2000);
	?joined(common,MainWp);
	//cartago.set_current_wsp(MainWp);
	broadcast_announce_WTS_creation(SpecId,AMArtifactName);
	
	//cartago.set_current_wsp(LocalWp);
	linkArtifacts(AccessManagerId,"mygraph",SolutionGraphId)[wsp(my_spec_wp)];
	.print("linking ", AccessManagerId, " to ", SolutionGraphId);	
	
	+solution_management_info(SpecId,AccessManagerId,SolutionGraphId);
	+auction_loop_delay(SpecIdString,50);														// this allows to change loop frequency during the execution
	
	.wait(500);
	set_initial_node(w([on(main),open(i1),open(i2),closed(i3),open(i4)]))[artifact_id(AccessManagerId)];
	.wait(500);
	!auction_loop(SpecId);
.

+!auction_loop(SpecId)
<-
	?solution_management_info(SpecId,AccessManagerId,_);
	
	
	startAuction[artifact_id(AccessManagerId)];
	.wait(100);
	closeAuction[artifact_id(AccessManagerId)];
	
	get_number_of_nodes(N)[artifact_id(AccessManagerId)];
	?num_nodi(OLD_N);
	
	if (N > OLD_N) {
	 	.print("num nodi ",N,"    (old=",OLD_N,")");	
		//	print_graph[artifact_id(AccessManagerId)];
		//	.println("=============================================");
	}
	-+num_nodi(N);

	?iterazioni(I);
	if (I<200) {
		?auction_loop_delay(SpecIdString,AuctionDelay);
		-+iterazioni(I+1);
		.wait(AuctionDelay);
		!!auction_loop(SpecId);
	} else {
		print_graph[artifact_id(AccessManagerId)];
	}
.