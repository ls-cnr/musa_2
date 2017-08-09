// Agent explorer1 in project musa_2

/* Initial beliefs and rules */

/* Initial goals */

!start.


{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization
//{ include("$jacamoJar/templates/org-obedient.asl") }

/* Plans */

+!start
<-
	.my_name(MyName1);

	?focused(common,agent_directory, ArtId);
	register(MyName1)[artifact_id(ArtId)];

.

/*
 * REACT TO WTS CREATION
 * by
 * 1) preparing a local WTS artifact with empty list of nodes
 * 2) preparing an empty list of nodes-to-visit and  of visited-nodes
 * 3) preparing an empty list of expanded-nodes
 * 4) focuses on global WTS artifact for New Node Event
 * 5) start a Expand-Evaluation Loop
 */
+announcement_WTS_creation(SpecIdString,Workspace,GraphAccessManager)
<-
	.println("new WTS");
	.term2string(WP, Workspace)
	joinRemoteWorkspace(WP,"194.119.214.197",RemoteWS);
	.println("joining ",RemoteWS);
	focusWhenAvailable(GraphAccessManager);
	.println("focused new WTS");

	.my_name(MyName1);
	.concat("pe_",SpecIdString,ArtifactNameTemp);
	.concat(MyName1, ArtifactNameTemp, ArtifactName);
	makeArtifact(ArtifactName,"selfconf.ProblemExplorationArtifact2",[],PEId);
	//makeArtifact("copia","selfconf.ProblemExplorationArtifact",[],PEId2);
	.println("Created ", ArtifactName ," for ( ", SpecIdString," ) ArtifactName: ", PEId);

	+problem_exploration_info(SpecIdString,ArtifactName);	// this for storing essential info about the problem exploration
	+expand_loop_dalay(SpecIdString,200);				// this allows to change loop frequency during the execution

	!expand_local_graph_loop(SpecIdString);

	.abolish( announcement_WTS_creation(SpecIdString,GraphAccessManager) );
.

+announcement_new_node(SpecIdString,Node)
<-
	Node = enode(W,TokenList,Score,Exit);
	//.println("new node - w:",W," tokens:",TokenList," score:",Score);
	//.println("New node to expand.");
	?problem_exploration_info(SpecIdString,PEId);
	addToVisit(Node);
.

+announcement_new_auction(SpecIdString,AuctionId)[artifact_id(AccessManagerId)]
<-
	?problem_exploration_info(SpecIdString,PEId);
	getMostPromisingExpansion(Expansion);

	.my_name(MyName);

	if(Expansion \== null_expansion){
		//.println("Expansion:",Expansion);
		Expansion = expansionNode(_,_,Score,_,_);
		bid(AuctionId,MyName,Score) [artifact_id(AccessManagerId)];
		-+placed_bid(AuctionId,SpecIdString,Expansion);	// remember the Expansion selected for bidding
	}
.

+announcement_winner_auction(SpecIdString,AuctionId,WinnerName)[artifact_id(AccessManagerId)]
:
	.my_name(Me) & .term2string(Me,WinnerName)
<-
	?placed_bid(AuctionId,SpecIdString,Expansion);
	apply_changes(Expansion) [artifact_id(AccessManagerId)];
	removeWinnerNode(Expansion);
.


+!expand_local_graph_loop(SpecIdString)
<-
	?problem_exploration_info(SpecIdString,PEId);
	expand_local_graph;
	?expand_loop_dalay(SpecIdString,ExpandLoopDelay);
	.wait(ExpandLoopDelay);
	!expand_local_graph_loop(SpecIdString);
.
