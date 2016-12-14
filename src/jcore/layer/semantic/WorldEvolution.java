package layer.semantic;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import layer.awareness.DomainEntail;
import layer.semantic.evolution.EvolutionOperator;

/* *********************************************************/
/**
 * The Class WorldEvolution.
 * @author icar-aose
 * @author Javadoc Author Mirko Zichichi
 * @version 1.0.0
 */                                
/* *********************************************************/

public class WorldEvolution {
	
	/** The assumptions. */
	private AssumptionSet assumptions;
	
	/** The evo. */
	private LinkedList<StateOfWorld> evo;
	
	/** The checker. */
	private DomainEntail checker;

	/**
	 * Instantiates a new world evolution.
	 *
	 * @param K the k
	 * @param W the w
	 */
	public WorldEvolution(AssumptionSet K,StateOfWorld W) {
		evo= new LinkedList<StateOfWorld>();
		evo.add(W);
		assumptions = K;
		
		checker = DomainEntail.getInstance();
	}
	
	/**
	 * Adds the evolution.
	 *
	 * @param TX the tx
	 */
	public void addEvolution(Collection<EvolutionOperator> TX) {
		int last_index = evo.size()-1;
		StateOfWorld W_last = evo.get(last_index);
		
		StateOfWorld W = W_last.clone();
		Iterator<EvolutionOperator> it = TX.iterator();
		while (it.hasNext()) {
			EvolutionOperator op = it.next();
			W = op.apply_transformation(W);
		}
		
		evo.add(W);
	}
	
	/**
	 * Test T C FS.
	 *
	 * @param tc the tc
	 * @param fs the fs
	 * @return true, if successful
	 */
	public boolean test_TC_FS(Condition tc, Condition fs) {
		int state = 0;		// 0 = test TC, 1 = test FS, 2 = satisfied
		
		Iterator<StateOfWorld> it = evo.iterator();
		while (it.hasNext()) {
			StateOfWorld W_test = it.next();
			
			if (state==0) {
			
				boolean test = checker.entailsCondition(W_test, assumptions, tc);
				if (test==true)
					state++;
			
			} else if (state == 1) {
			
				boolean test = checker.entailsCondition(W_test, assumptions, fs);
				if (test==true)
					state++;		
			
			}
			
		}
		
		if (state==2)
			return true;
		
		return false;
	}
	
}
