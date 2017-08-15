/* MUSA 2.0 Explorer agent */

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
{ include("$jacamoJar/templates/org-obedient.asl") }

myArtifact("selfconf.ProblemExplorationArtifact1").

!start.

+!start
<-
	?focused(common,agent_directory, ArtId); .my_name(Me);
	register(Me)[artifact_id(ArtId)].

+!expand_local_graph_loop(SpecIdString)
<-
	?problem_exploration_info(SpecIdString,PEId);
	expand_local_graph;
	?expand_loop_delay(SpecIdString,ExpandLoopDelay);
	.wait(ExpandLoopDelay);
	!expand_local_graph_loop(SpecIdString).

/*
 * REACT TO WTS CREATION by
 * 1. preparing a local WTS artifact with empty list of nodes
 * 2. preparing an empty list of nodes-to-visit and  of visited-nodes
 * 3. preparing an empty list of expanded-nodes
 * 4. focusing on global WTS artifact for New Node Event
 * 5. starting an Expand-Evaluation Loop
 */
+announcement_WTS_creation(SpecIdString,Workspace,GraphAccessManager)
<-
	.println("new WTS");
	.term2string(WP, Workspace)
	joinRemoteWorkspace(WP,"194.119.214.197",RemoteWS);
	.println("joining ",RemoteWS);
	focusWhenAvailable(GraphAccessManager);
	.println("focused new WTS");

	.my_name(Me);
	.concat("pe_",SpecIdString,ArtifactNameTemp);
	.concat(Me, ArtifactNameTemp, ArtifactName);

	?myArtifact(Art);
	makeArtifact(ArtifactName, Art, [], PEId);
	.println("Created ", ArtifactName ," for ( ", SpecIdString," ) ArtifactName: ", PEId);

	// this for storing essential info about the problem exploration
	+problem_exploration_info(SpecIdString,ArtifactName);

	// this allows to change loop frequency during the execution
	+expand_loop_delay(SpecIdString, 200);

	!expand_local_graph_loop(SpecIdString);

	.abolish( announcement_WTS_creation(SpecIdString,GraphAccessManager) ).

+announcement_new_node(SpecIdString,Node)
<-
	Node = enode(W,TokenList,Score,Exit);
	//.println("new node - w:",W," tokens:",TokenList," score:",Score);
	//.println("New node to expand.");
	?problem_exploration_info(SpecIdString,PEId);
	addToVisit(Node).

+announcement_new_auction(SpecIdString,AuctionId)[artifact_id(AccessManagerId)]
<-
	?problem_exploration_info(SpecIdString,PEId);
	getMostPromisingExpansion(Expansion);

	if(Expansion \== null_expansion){
		//.println("Expansion:",Expansion);
		Expansion = expansionNode(_,_,Score,_,_);
	    .my_name(MyName);
		bid(AuctionId,MyName,Score)[artifact_id(AccessManagerId)];
		// remember the Expansion selected for bidding
		-+placed_bid(AuctionId,SpecIdString,Expansion);
	}.

+announcement_winner_auction(SpecIdString,AuctionId,WinnerName)[artifact_id(AccessManagerId)]
:
	.my_name(Me) & .term2string(Me,WinnerName)
<-
	?placed_bid(AuctionId,SpecIdString,Expansion);
	apply_changes(Expansion)[artifact_id(AccessManagerId)];
	removeWinnerNode(Expansion).
