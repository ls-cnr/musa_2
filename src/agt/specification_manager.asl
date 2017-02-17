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


/* Initial goals */

!new_injection("spec01").

/* Plans */

+!new_injection(SpecId) 
: 
	true 
<- 
	.wait(1000);
	
	.concat("SG_", SpecId, SGArtifactName);
	.concat("AM_", SpecId, AMArtifactName);

	makeArtifact(AMArtifactName,"selfconf.AccessManagerArtifact",[SpecId],AccessManagerId);
	makeArtifact(SGArtifactName,"selfconf.SolutionGraphArtifact",[],SolutionGraphId);	
	
	broadcast_announce_WTS_creation(SpecId,AMArtifactName);
	
	linkArtifacts(AccessManagerId,"mygraph",SolutionGraphId);
	.print("linking ", AccessManagerId, " to ", SolutionGraphId);	
	
	+solution_management_info(SpecId,AccessManagerId,SolutionGraphId);
	+auction_loop_delay(SpecIdString,300);														// this allows to change loop frequency during the execution
	
	.wait(1000);
	set_initial_node(world([]))[artifact_id(AccessManagerId)];
	
	!auction_loop(SpecId);
.

+!auction_loop(SpecId)
<-
	?solution_management_info(SpecId,AccessManagerId,_);
	
	startAuction[artifact_id[AccessManagerId]];
	.wait(100);
	closeAuction[artifact_id[AccessManagerId]];
	
	?auction_loop_delay(SpecIdString,AuctionDelay);
	.wait(AuctionDelay);
	!!auction_loop(SpecId);
.