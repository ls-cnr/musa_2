/* case-manager plans */
+!solve_request_loop(SpecId,RequestId)
<-
	!wait_configuration(SpecId,/*-->*/ ConfId);
	+contract(SpecId,RequestId,ConfId);
. // FINAL, not tested

+!wait_configuration(SpecId,/*-->*/ ConfId)
<-
	!select_configuration(SpecId,/*-->*/ ConfId);
	if (Conf \== zero_conf) {
		ConfId = Conf;
	} else {
		?delay(wait_configuration,Delay);
		.wait(Delay);
		!!wait_configuration(SpecId,ConfId);
	}
. // FINAL, not tested


+!select_configuration(SpecId,/*-->*/ ConfId)
<-
	.println("selected a configuration for ",SpecId);
	ConfId = dummy_conf;
. // DUMMY, questa e' un azione dell'artefatto GraphSolution

+!mark_failure(SpecId,RequestId,ConfId,CapabilityId)
<-
	true
. // DUMMY, questa e' azione dell'artefatto GraphSolution ---> it could work as Davide's black-board

+!select_alternative_configuration(SpecId,RequestId,ConfId,/*-->*/ AltConfId) /* DUMMY ---> concretely it is a loop */
<-
	.println("selected a new configuration for ",SpecId," and ",RequestId);
	ConfId = dummy_conf;
. // DUMMY, come wait_configuration ma AltConfId deve essere diverso da ConfId