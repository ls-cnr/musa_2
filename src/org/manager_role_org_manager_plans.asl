+!lead_organization_formation(SpecId,RequestId,ConfId)
<-
	.println("enacted the configuration for ",SpecId," and ",RequestId);
. // DUMMY, todo

+!switch_configuration(SpecId,RequestId,ConfId,AltConfId)
<-
	.println("enacted the new configuration for ",SpecId," and ",RequestId);
. // DUMMY, todo

+!dismiss_organization(SpecId,RequestId,ConfId)
<-
	.println("dismiss the configuration for ",SpecId," and ",RequestId);
. // DUMMY, todo

+!run_orchestration(SpecId,RequestId,ConfId)
<-
	.println("orchestration starts for ",SpecId," and ",RequestId);
. // DUMMY, todo

+!stop_orchestration(SpecId,RequestId,ConfId)
<-
	.println("orchestration stops for ",SpecId," and ",RequestId);
. // DUMMY, todo

+!restart_orchestration(SpecId,RequestId,ConfId)
<-
	.println("orchestration re-starts for ",SpecId," and ",RequestId);
. // DUMMY, todo

+!solve_failure(SpecId,RequestId,ConfId,CapabilityId)
<-
	!find_alternative_service(SpecId,RequestId,ConfId,CapabilityId,AltId);
	if (AltId \== no_alternative) {
		!switch_service(SpecId,RequestId,ConfId,CapabilityId,AltId);
	} else {
		!force_reconfiguration(SpecId,RequestId,ConfId);
		!restart_orchestration(SpecId,RequestId,ConfId);
	}
.

+!find_alternative_service(SpecId,RequestId,ConfId,CapabilityId,AltId)
<-
	true
. // DUMMY, todo

+!switch_service(SpecId,RequestId,ConfId,CapabilityId,AltId)
<-
	true
. // DUMMY, todo