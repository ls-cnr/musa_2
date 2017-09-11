/* MUSA 2.0 Configuration manager agent */

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }
{ include("$jacamoJar/templates/org-obedient.asl") }

{ include("inc/common_conf_manager.asl") }

!start.

+!start
<-
	.print("Generating graph");

	SpecId="spec01";

	.concat("CM_", SpecId, CMArtifactName);
	makeArtifact(CMArtifactName,"selfconf.ConfigSelector", [SpecId], AccessManagerId);

	//!case_study;
	!toy_model;
.