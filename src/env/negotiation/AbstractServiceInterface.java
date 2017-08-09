/* Abstract Service environment artifact interface */

package negotiation;

import cartago.OPERATION;
import cartago.OpFeedbackParam;

public interface AbstractServiceInterface {

  /**
   * Initial belief base of each SP created via createProviders
   * operation (see below).
   * <p>
   * The literal <b>parameters</b> must have a numeric list as
   * single term: this list represents the SP indifference curve
   * parameters. For instance, in our test case scenario a 2D
   * QoS attribute space and Cobb-Douglas indifference curves only
   * are considered, so the parameters are: [alpha, beta, gamma,
   * utility].
   * <p>
   * The literal <b>id</b> must have a numeric single term, which
   * will represent the SP identification number. This number can
   * be useful to the Artifact for coping with the asynchronicity
   * of the SP messages. If you don't plan to use it, you can set
   * it to a constant number.
   * <p>
   * The literal <b>service</b> must have a single string term,
   * representing the service name type the AS is referring to.
   * <p>
   * The literal <b>as</b> must have a single string term,
   * representing the AS agent name.
   * <p>
   * The literal <b>as</b> must have a single string term,
   * representing the name of the SC agent that started the
   * negotiation.
   * <p>
   * The literal <b>offer</b> must have a numeric list as single
   * term. This has to have size m, with m being the number of
   * QoS attributes considered in the negotiation.
   */
  public static final String bb = "parameters(%s),id(%s),service(%s),as(%s),sc(%s),offer(%s)";

  /**
   * Artifact initialisation method.
   *
   * @param ASname       AS name
   * @param serviceName  service name
   * @param URI          URI for WSDL containing AS related infos
   * @param deadline     maximum number of rounds allowed for negotiation
   */
  void init(String ASname, String serviceName, String URI, int deadline);

  /**
   * Instantiates service provider data entries.
   * <p>
   * Objectives:
   * <ol>
   * <li>find all currently existing services matching serviceURI</li>
   * <li>create a service_provider agent for each one</li>
   * </ol>
   *
   * @param SCname  name of SC agent for this negotiation
   * @see negotiation.Demo.AbstractService  for an implementation example
   */
  @OPERATION void createProviders(String SCname);

  /**
   * Gets number of SPs.
   * <p>
   * Example usage within a Jason agent plan: "getNumberOfSP(N); .print(N);"
   */
  @OPERATION void getNumberOfSP(OpFeedbackParam<Integer> numberOfSP);

}
