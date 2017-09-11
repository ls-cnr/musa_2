/* MUSA 2.0 Specification manager agent */

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
{ include("$jacamoJar/templates/org-obedient.asl") }

// TODO aggiungere goals e domain assumptions sotto forma di beliefs

!start.

+!start
<-
	.print("Hello, injecting spec01");
	!new_injection("spec01").

+!new_injection(SpecId)
<-
	.my_name(Me); .concat(Me, "_wsp", WspName);
	createWorkspace(WspName);
	joinWorkspace(WspName, WspId);
	//cartago.set_current_wsp(WspId);

	.concat("SG_", SpecId, SGArtifactName);
	makeArtifact(SGArtifactName,"selfconf.SolutionGraphArtifact",[],SolutionGraphId)[wsp(WspName)];
	.concat("AM_", SpecId, AMArtifactName);
	makeArtifact(AMArtifactName,"selfconf.AccessManagerArtifact",[SpecId],AccessManagerId)[wsp(WspName)];

	?joined(common,MainWp); .wait(1000);

	//cartago.set_current_wsp(MainWp);
	broadcast_announce_WTS_creation(SpecId,WspName,AMArtifactName);

	//cartago.set_current_wsp(LocalWp);
	linkArtifacts(AccessManagerId,"mygraph",SolutionGraphId)[wsp(WspName)];
	.print("linking ", AccessManagerId, " to ", SolutionGraphId);

	+solution_management_info(SpecId,AccessManagerId,SolutionGraphId);
	+auction_loop_delay(SpecIdString, 300); // this allows to change loop frequency during the execution

	.wait(1000);
	set_initial_node(w([order(an_order),available(an_order),user(a_user),user_data(the_user_data)]))[artifact_id(AccessManagerId)];

	!auction_loop(SpecId).

+!auction_loop(SpecId)
<-
	?solution_management_info(SpecId,AccessManagerId,_);

	get_number_of_nodes(N)[artifact_id(AccessManagerId)];
	.print("number of nodes: ",N);

	startAuction[artifact_id(AccessManagerId)];
	.wait(200);
	closeAuction[artifact_id(AccessManagerId)];

	if (N < 19) {
		?auction_loop_delay(SpecIdString,AuctionDelay);
		.wait(AuctionDelay);
		!auction_loop(SpecId);
	} else {
		print_graph[artifact_id(AccessManagerId)];
	}.
