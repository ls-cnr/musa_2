!start.

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have a agent that always complies with its organization
//{ include("$jacamoJar/templates/org-obedient.asl") }

/* Plans */


+!start
<-
	.my_name(MyNamePrint);
.

+announcement_WTS_creation(SpecIdString,GraphAccessManager)
<-
	focusWhenAvailable(GraphAccessManager);
	.wait(20000);
.
