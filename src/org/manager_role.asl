/**********************************************************/
/* THE MANAGER ROLE                                                                              
 * This file contains:                                                                                      
 * + protocols for handling the injection, change and retreat new specifications
 * + protocols for coordinating distribute search in the problem space 
 * + protocols for service negotiation
 * + protocols for coordinating the formation of an organization
 * + protocols for managing the orchestration
 */                                
/**********************************************************/



 
/************events****************************************/


/* spec-manager 'external' events */
+spec_injection(SpecId)
<-
	!check_main_monitor(SpecId,/*-->*/ MonitorId);			// verify if the agent owns the capability to monitor the start of the process
	if (MonitorId \== void) {
		!!start_main_monitor(SpecId,MonitorId);
		!!start_self_configuration(SpecId);
		+ready_for_accepting_request(SpecId);
	}
. // FINAL, not tested

+spec_change(SpecId)
<-
	!forall_contract_force_receonfiguration(SpecId);
. // FINAL, not tested


+spec_retreat(SpecId)
<-
	!forall_contract_force_dismiss_organization(SpecId);
	!dismiss_problem_space(SpecId);
. // FINAL, not tested



/* case-manager 'external' events */
+request(SpecId,RequestId)
:
	ready_for_accepting_request(SpecId)
<-
	!solve_request_loop(SpecId,RequestId)
. // FINAL, not tested
+request(SpecId,RequestId)
<-
	.println("I can not accept request for ", SpecId, " now");
. // DUMMY, la final version deve mandare indietro un segnale di qualche tipo




/* org-manager 'external' events */
+contract(SpecId,RequestId,ConfId)
<-
	!lead_organization_formation(SpecId,RequestId,ConfId);
	!run_orchestration(SpecId,RequestId,ConfId);
. // FINAL, not tested


/* org-manager 'internal' events */
+failure(SpecId,RequestId,ConfId,CapabilityId)
<-
	!stop_orchestration(SpecId,RequestId,ConfId);
	!mark_failure(SpecId,RequestId,ConfId,CapabilityId);
	!solve_failure(SpecId,RequestId,ConfId,CapabilityId);
. // FINAL, not tested


