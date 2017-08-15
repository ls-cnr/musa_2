/* Service Compositor environment artifact interface */

package negotiation;

import cartago.OPERATION;
import cartago.OpFeedbackParam;
import jason.asSyntax.ListTerm;
import jason.asSyntax.Literal;

public interface ServiceCompositorInterface {

	/**
	 * Artifact initialisation method.
	 *
	 * @param QoS
	 *            Quality of Service attributes (list of strings in Jason)
	 * @param C
	 *            Global constraints (list of numbers in Jason)
	 * @param deadline
	 *            Maximum number of turns allowed
	 */
	void init(Object[] QoS, Object[] C, int deadline);

	/**
	 * Verifies current negotiation status.
	 * <p>
	 * The first parameter is a literal in the form 'offers(L)', where L is the list
	 * of current standing offers, and:
	 * <ol>
	 * <li>each of its elements is a binary compound term in the form
	 * 'offers(ASname, LO)', where LO is a list;</li>
	 * <li>each element of LO is a binary compound term in the form 'offer(SPname,
	 * O)', where O is a list with arity m, with m being the number of QoS.</li>
	 * </ol>
	 * <p>
	 * The bestOffersPackage feedback parameter must be a vector of literals. Each
	 * literal must have the following form: provider(ASname, SPname, offer)
	 * <p>
	 * The remainingIssues feedback parameter must be list of numbers with length m,
	 * where m is the number of QoS attributes.
	 * <p>
	 * The stoppingCondition feedback parameter is a flag which allows to stop the
	 * negotiation. You can implement a criteria which sets it to true when, for
	 * each AS, reference point and best offer already reached their minimum
	 * distance, or started getting close too slowly. This can save negotiation
	 * rounds. Otherwise, always set it to false.
	 * <p>
	 * Parameters agreement, paretoOptimalAgreement and stoppingCondition must be
	 * set as numeric booleans (0 is false, 1 is true).
	 *
	 * @param offers
	 * @param bestOffersPackage
	 *            More-is-better vector b
	 * @param remainingIssues
	 *            Vector R
	 * @param agreement
	 * @param paretoOptimalAgreement
	 * @param stoppingCondition
	 */
	@OPERATION
	public void currentStatus(String offers, OpFeedbackParam<Literal[]> bestOffersPackage,
			OpFeedbackParam<ListTerm> remainingIssues, OpFeedbackParam<Integer> agreement,
			OpFeedbackParam<Integer> paretoOptimalAgreement, OpFeedbackParam<Integer> stoppingCondition);

	/**
	 * Calculates reference point.
	 *
	 * @param ASname
	 *            AS which we are calculating rp for
	 * @param refPoint
	 *            Output rp (a list of m numbers in Jason, with m being the number
	 *            of QoS)
	 */
	@OPERATION
	public void calculateRefPoint(String ASname, OpFeedbackParam<ListTerm> refPoint);

}
