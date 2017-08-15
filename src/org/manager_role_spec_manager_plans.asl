/* spec-manager plans */
+!check_main_monitor(SpecId,/*-->*/ MonitorId)
<-
	MonitorId=dummy_monitor
. // DUMMY, la final version deve verificare la presenza di una monitor capability da usare per attivare il processo (accettare richieste)

+!start_main_monitor(SpecId,MonitorId)
:
	MonitorId = dummy_monitor
<-
	?delay(dummy_monitor, /* -> */ Delay);
	.wait(Delay);
	+request(SpecId, dummy_request);
. // DUMMY, la final version deve attivare la monitor capability che poi aggiunge delle request

+!start_self_configuration(SpecId)
<-
	!setup_problem_space(SpecId);		// creates a SolutionGraph and associates the SpecId to the ArtifactId

	+ready_for_self_config(SpecId);		// gestisce il cilco di vita della self-conf (es: puo' essere messa in mausa)
	!lead_self_conf_loop(SpecId);
. // FINAL, not tested

+!setup_problem_space(SpecId)
<-
	.println("created Problem Space Artifact for ",SpecId);
.// DUMMY, questa operazione crea un artefatto di tipo solution graph

+!lead_self_conf_loop(SpecId)
:
	ready_for_self_config(SpecId)
<-
	!look_for_configurations(SpecId);	// inspect the artifact to discover configurations

	?delay(search_configuration,Delay);
	.wait(Delay);
	!!lead_self_conf_loop(SpecId);
.// FINAL, not tested
+!lead_self_conf_loop(SpecId)
:
	pause_for_self_config(SpecId)
<-
	?delay(search_configuration,Delay);
	.wait(Delay);
	!!lead_self_conf_loop(SpecId);
.// FINAL, not tested
+!lead_self_conf_loop(SpecId)
<-
	.println("loop di self-configuration terminato");
.// DUMMY, forse non deve fare nulla se non terminare l'intenzione


+!look_for_configurations(SpecId)
<-
	.println("looking the Problem Space Artifact ",SpecId," for configurations");
.// DUMMY, azione dell'artefatto solution graph per cercare una soluzione nel grafo


+!forall_contract_force_reconfiguration(SpecId)
<-
	.findall(contract(SpecId,R,C),contract(SpecId,R,C),ContractList);
	for (.member(X,ContractList)) {
		X = contract(SpecId,RequestId,ConfId);
		!stop_orchestration(SpecId,RequestId,ConfId);
		!force_reconfiguration(SpecId,RequestId,ConfId);
		!restart_orchestration(SpecId,RequestId,ConfId);
	}
.// FINAL, not tested

+!force_reconfiguration(SpecId,RequestId,ConfId)
<-
	!select_alternative_configuration(SpecId,RequestId,ConfId,/*-->*/ AltConfId);
	!switch_configuration(SpecId,RequestId,ConfId,AltConfId);					// force changing the organization consequently
. // FINAL, not tested



+!forall_contract_force_dismiss_organization(SpecId)
<-
	.findall(contract(SpecId,R,C),contract(SpecId,R,C),ContractList);
	for (.member(X,ContractList)) {
		X = contract(SpecId,RequestId,ConfId);
		!stop_orchestration(SpecId,RequestId,ConfId);
		!dismiss_organization(SpecId,RequestId,ConfId);
	}
. // FINAL, not tested