package layer.awareness;

import java.util.ArrayList;
import java.util.HashMap;

import layer.awareness.goaltree.*;
import layer.semantic.Condition;
import petrinet.logic.*;

/**
 * Used to construct a PetriNet from a GoalModel.
 */
public class Net {
	
	/** The GoalModel used to construct the Net. */
	private GoalModel model;
	 
	/** The PetriNet. It contains Places, Transitions and Arcs */
	private Petrinet pn;
	
	/** The structure that maintains associations between transitions and goals conditions (trigger condition, final state)*/
	private HashMap<Transition, Condition> labels;
	
	/** These variables are used to name places, transitions and arcs in a correct order */
	private static int p = 0;
	private static int t = 0;
	private static int a = 0;
	
	/**
	 * Instantiates a new Net.
	 *
	 * @param m the GoalModel
	 */
	public Net( GoalModel m ) {
		pn = new Petrinet("petrinet");
		model = m;
		labels = new HashMap<>();
		
		/*Add first place*/
		pn.place("p"+p++);
	}
	
	/**
	 * Get first Place in the net.
	 *
	 * @return the first Place
	 */
	public Place getFirstPlace() {
		return pn.getPlaces().get(0);
	}
	
	/**
	 * tarts the construction
	 *
	 * @return last Place created
	 */
	public Place construct(){
		return netConstruction(getFirstPlace(), null, model.getRoot());
	}
	
	/**
	 * The main method for the net construction. It's a recursive method that recalls himself from the 
	 *  templates, each time to construct a smaller part of the Net.
	 *
	 * @param place First Place used in this part of Net
	 * @param last Last Place used in this part of Net (could be null if it isn't necessary to have one (only necessary within conditional template))
	 * @param g_root The subtree root used in this part of Net
	 * @return last Place created
	 */
	private Place netConstruction( Place place, Place last, Goal g_root ) {
		ArrayList<RefinementArc> arcs = model.getArcs(g_root); //root arcs
		
		if( arcs == null )								//if the root has no arcs								
			return basicTemplate(place, last, g_root);	//base case
		else
			if( arcs.get(0) instanceof AndArc )							//if the root has And Arcs
				return parallelTemplate(place, last, g_root, arcs);		//recursion	with parallel template	
			else if( arcs.get(0) instanceof OrArc )						//if the root has Or Arcs
				return conditionalTemplate(place, last, g_root, arcs);	//recursion	with conditional template
		return last;
	}
	
	/**
	 * Basic template. It's the simplest construction, from the first Place(param), it adds an Arc to a new 
	 *  Transition (that represents the Goal's trigger condition), then to a new Place and then to another
	 *  Transition (that represents the Goal's final state). Finally it adds an Arc to the Place passed as
	 *  last(param) or to a new Place(returned).
	 *
	 * @param place First Place used in this template
	 * @param last Last Place used in this template (could be null if it isn't necessary to have one)
	 * @param goal The goal used in this template
	 * @return last Place created
	 */
	private Place basicTemplate( Place place, Place last, Goal goal ) {
		//First Transition, it represents represents Goal's trigger condition
		Transition t1 = pn.transition("t"+t++);
		addTriggerCondition(t1, goal);
		//Second Transition, it represents Goal's final state
		Transition t2 = pn.transition("t"+t++);
		addFinalState(t2, goal);								
		
		//Adding first block Place-Transition-Place
		pn.arc("arc"+a++, place, t1);
		place = pn.arc("arc"+a++, t1, pn.place("p"+p++)).getPlace();
			
		//Adding second block Transition-Place
		pn.arc("arc"+a++, place, t2);
		if( last == null )
			return pn.arc("arc"+a++, t2, pn.place("p"+p++)).getPlace();
		else
			return pn.arc("arc"+a++, t2, last).getPlace();
	}
	
	/**
	 * Parallel template. It starts a parallel construction from the first Transition, added with an 
	 *  Arc from the first Place(param). After the construction in subgoals, it reconnects the last 
	 *  places in subtemplates to a single new Transition. Finally it adds an Arc to the Place passed as
	 *  last(param) or to a new Place(returned).
	 *
	 * @param place First Place used in this template
	 * @param last Last Place used in this template (could be null if it isn't necessary to have one)
	 * @param goal The goal used in this template
	 * @param arcs Goal's arcs
	 * @return last Place created
	 */
	private Place parallelTemplate( Place place, Place last, Goal goal, ArrayList<RefinementArc> arcs ) {
		//First Transition, it represents represents Goal's trigger condition
		Transition t1 = pn.transition("t"+t++);
		addTriggerCondition(t1, goal);
		//Second Transition, it represents Goal's final state
		Transition t2 = pn.transition("t"+t++);
		addFinalState(t2, goal);
		
		//Adding first Transition
		pn.arc("arc"+a++, place, t1);
		
		//Parallel Constructing
		for( RefinementArc arc : arcs ){
			//Place to use first in subtemplate
			place = pn.arc("arc"+a++, t1, pn.place("p"+p++)).getPlace();
			//subtemplate
			place = netConstruction(place, null, arc.nextNode);
			//Transition where all the subtemplates connect
			pn.arc("arc"+a++, place, t2);
		}
		
		//Adding last Place
		if( last == null )
			return pn.arc("arc"+a++, t2, pn.place("p"+p++)).getPlace();
		else
			return pn.arc("arc"+a++, t2, last).getPlace();
	}

	/**
	 * Conditional template. It starts a conditional construction from the first Transition, added with an 
	 *  Arc from the first Place(param). All the constructions in subgoals start from the same Place
	 *  and finish in the same Place (orPlace). Then it adds an Arc from orPlace to a new Transition, 
	 *  and finally adds an Arc to the Place passed as last(param) or to a new Place(returned).
	 *
	 * @param place First Place used in this template
	 * @param last Last Place used in this template (could be null if it isn't necessary to have one)
	 * @param goal The goal used in this template
	 * @param arcs Goal's arcs
	 * @return last Place created
	 */
	private Place conditionalTemplate( Place place, Place last, Goal goal, ArrayList<RefinementArc> arcs ) {
		//First Transition, it represents represents Goal's trigger condition
		Transition t1 = pn.transition("t"+t++);
		addTriggerCondition(t1, goal);
		//Second Transition, it represents Goal's final state
		Transition t2 = pn.transition("t"+t++);
		addFinalState(t2, goal);
		
		//Adding first block Place-Transition-Place
		pn.arc("arc"+a++, place, t1);
		place = pn.arc("arc"+a++, t1, pn.place("p"+p++)).getPlace();
		
		//Place to use at the end of subtemplates
		Place orPlace = pn.place("p"+p++);		
		
		//Conditional constructing
		for( RefinementArc arc : arcs ){
			//subtemplate
			netConstruction(place, orPlace, arc.nextNode);
		}
		
		//Adding last block Place-Transition-Place
		pn.arc("arc"+a++, orPlace, t2);
		if( last == null )
			return pn.arc("arc"+a++, t2, pn.place("p"+p++)).getPlace();
		else
			return pn.arc("arc"+a++, t2, last).getPlace();
		
	}
	
	/**
	 * Gets the petrinet.
	 *
	 * @return the petrinet
	 */
	public Petrinet getPetrinet() {
		return pn;
	}
	
	/**
	 * Adds the trigger condition to a Transition.
	 *
	 * @param transition 
	 * @param goal
	 */
	private void addTriggerCondition( Transition transition, Goal goal) {
		labels.put(transition, goal.getTrigger_condition());
	}
	
	/**
	 * Adds the final state to a Transition.
	 *
	 * @param transition
	 * @param goal
	 */
	private void addFinalState( Transition transition, Goal goal) {
		labels.put(transition, goal.getFinal_state());
	}
	
}
