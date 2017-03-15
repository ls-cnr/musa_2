// Agent specification_manager in project musa_2
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
// uncomment the include below to have a agent that always complies with its organization  
//{ include("$jacamoJar/templates/org-obedient.asl") }


/* Initial beliefs and rules */

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
	
	?joined(common,MainWp);
	//cartago.set_current_wsp(MainWp);
	broadcast_announce_WTS_creation(SpecId,my_spec_wp,AMArtifactName);
	
	//cartago.set_current_wsp(LocalWp);
	linkArtifacts(AccessManagerId,"mygraph",SolutionGraphId)[wsp(my_spec_wp)];
	.print("linking ", AccessManagerId, " to ", SolutionGraphId);	
	
	+solution_management_info(SpecId,AccessManagerId,SolutionGraphId);
	+auction_loop_delay(SpecIdString,300);														// this allows to change loop frequency during the execution
	
	.wait(1000);
	set_initial_node(w([order(an_order),available(an_order),user(a_user),user_data(the_user_data)]))[artifact_id(AccessManagerId)];
	
	!auction_loop(SpecId);
.

+!auction_loop(SpecId)
<-
	?solution_management_info(SpecId,AccessManagerId,_);
	
	get_number_of_nodes(N)[artifact_id(AccessManagerId)];
	.print("num nodi ",N);	
	
	startAuction[artifact_id(AccessManagerId)];
	.wait(100);
	closeAuction[artifact_id(AccessManagerId)];
	
	if (N<19) {
		?auction_loop_delay(SpecIdString,AuctionDelay);
		.wait(AuctionDelay);
		!!auction_loop(SpecId);
	} else {
		print_graph[artifact_id(AccessManagerId)];
	}
.