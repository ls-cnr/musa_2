/* Example of a Service Compositor */

!start([ service("flight", "https://xml.org/flight.xml"),
         service("hotel", "https://xml.org/hotel.xml") ],
       ["time", "price"],
       [1, 1],
       10).

/**
 * Select the optimal composition for given service types list.
 *
 * @param AS list of service types in the form of (name, uri) string predicates:
 *        'name' refers to service type name, while
 *        'uri' refers to an URI of its description
 * @param QoS list of Quality of Service attributes
 * @param C list of global constraints (one for each QoS attribute)
 * @param T maximum number of rounds allowed in the negotiation
 */
+!start(AS, QoS, C, T)
<-
    .include("service_compositor.asl", SC);
    !!SC::start(AS, QoS, C, T);
    .wait("+SC::done", 60 * 1000); // wait until completion or some timeout
    ?SC::bestOffersPackage(B);
    if (SC::pareto_optimal_agreement(A) & A = 1) {
        .print("Pareto-optimal contract: ", B);
        +paretoOptimalContract(AS, QoS, C, T, B);
    } else {
    	if (SC::agreement(A) & A = 1) {
          .print("Contract: ", B);
          +contract(AS, QoS, C, T, B);
    	} else {
          .print("No contract currently available with given conditions");
    	};
    };
    .abolish(SC::_). // clear memory from namespace once done