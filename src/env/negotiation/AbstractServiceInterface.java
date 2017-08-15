/* Abstract Service environment artifact interface */

package negotiation;

import cartago.GUARD;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import jason.asSyntax.ListTerm;
import jason.asSyntax.Literal;

public interface AbstractServiceInterface {

	// Used by ASs

	/**
	 * Initial belief base of each SP created via createProviders operation (see
	 * below).
	 * <p>
	 * The literal <b>parameters</b> must have a numeric list as single term: this
	 * list represents the SP indifference curve parameters. For instance, in our
	 * test case scenario a 2D QoS attribute space and Cobb-Douglas indifference
	 * curves only are considered, so the parameters are: [alpha, beta, gamma,
	 * utility].
	 * <p>
	 * The literal <b>id</b> must have a numeric single term, which will represent
	 * the SP identification number. This number can be useful to the Artifact for
	 * coping with the asynchronicity of the SP messages. If you don't plan to use
	 * it, you can set it to a constant number.
	 * <p>
	 * The literal <b>service</b> must have a single string term, representing the
	 * service name type the AS is referring to.
	 * <p>
	 * The literal <b>as</b> must have a single string term, representing the AS
	 * agent name.
	 * <p>
	 * The literal <b>sc</b> must have a single string term, representing the name
	 * of the SC agent that started the negotiation.
	 * <p>
	 * The literal <b>offer</b> must have a numeric list as single term. This has to
	 * have size m, with m being the number of QoS attributes considered in the
	 * negotiation.
	 */
	public static final String bb = "parameters(%s),id(%s),service(%s),as(%s),sc(%s),offer(%s)";

	/**
	 * Artifact initialisation method.
	 *
	 * @param ASname
	 *            AS name
	 * @param serviceName
	 *            service name
	 * @param URI
	 *            URI containing AS related infos
	 * @param deadline
	 *            maximum number of rounds allowed for negotiation
	 */
	void init(String ASname, String serviceName, String URI, int deadline);

	/**
	 * A guard to assure the correct workflow of createProviders(...) function.
	 *
	 * @return true if each created SP has also correctly initialised, false
	 *         otherwise
	 */
	@GUARD
	public boolean initialised();

	/**
	 * Instantiates service provider data entries.
	 * <p>
	 * Objectives:
	 * <ol>
	 * <li>find all currently existing services matching URI</li>
	 * <li>create a service_provider agent for each one</li>
	 * </ol>
	 *
	 * @param SCname
	 *            name of SC agent for this negotiation
	 * @see negotiation.Demo.AbstractService for an implementation example
	 */
	@OPERATION
	void createProviders(String SCname);

	/**
	 * Gets number of SPs.
	 *
	 * @param numberOfSP
	 *            the variable to identify the number with
	 */
	@OPERATION
	void getNumberOfSP(OpFeedbackParam<Integer> numberOfSP);

	/**
	 * Gets the last offers proposed by SPs.
	 *
	 * @param offersList
	 *            the variable to identify the offers with: must be defined as a
	 *            list of offer(SPname, Offer) predicates, where SPname is the
	 *            identifier of an SP, and Offer is a numerical list of size m, with
	 *            m being the number of QoS attributes
	 */
	@OPERATION
	public void getStandingOffers(OpFeedbackParam<Literal[]> offersList);

	// Used by SPs

	/**
	 * An operation used by every SP to tell the AS via environment that it has been
	 * correctly initialised; its implementation depends on initialised() guard
	 * function implementation.
	 */
	@OPERATION
	public void providerInitialised();

	/**
	 * An operation used by every SP to send the AS its current offer.
	 *
	 * @param sp
	 *            a string identifier of the SP
	 * @param offer
	 *            a string version of the SP offer, which is a numerical tuple of
	 *            size m, with m being the number of QoS attributes
	 */
	@OPERATION
	public void sendOffer(String sp, String offer);

	/**
	 * An operation used by every SP to adjust its offer.
	 *
	 * @param refPoint
	 *            a string version of the reference point, which is a numerical
	 *            tuple of size m, with m being the number of QoS attributes
	 * @param id
	 *            a string identifier of this SP, (which might be) used by internal
	 *            logic
	 * @param paramsStr
	 *            a string version of the numeric list of parameters for the
	 *            indifference curve of this SP
	 * @param newOffer
	 *            the new counter-offer the SP is going to save in its BB: a
	 *            numerical list of size m, with m being the number of QoS
	 *            attributes
	 */
	@OPERATION
	public void adjustOffer(String refPoint, String id, String paramsStr, OpFeedbackParam<ListTerm> newOffer);

}
