package petrinet.logic;

import java.util.ArrayList;
import java.util.List;

public class Place 
extends PetrinetObject {

    // it's a magic number....
    public static final int UNLIMITED = -1;
    
    private int tokens = 0;
    private int maxTokens = 1;    

	private List<Arc> incoming = new ArrayList<Arc>(); 	//added
	private List<Arc> outgoing = new ArrayList<Arc>();	//added
    

    protected Place(String name) {
        super(name);
    }

    protected Place(String name, int initial) {
        this(name);
        this.tokens = initial;
    }

    /**
     * besitzt die Stelle mindestens so viele tokens?
     * 
     * il posto possiede almeno tanti token quanti sono passati come param?
     * 
     * @param threshold
     * @return
     */
    public boolean hasAtLeastTokens(int threshold) {
        return (tokens >= threshold);
    }

    /**
     * wŸrde die Stelle noch so viele Tokens aufnehmen kšnnen?
     * 
     * il posto è ancora in grado di prendere token?
     * @param newTokens
     * @return
     */
    public boolean maxTokensReached(int newTokens) {
        if (hasUnlimitedMaxTokens()) {
            return false;
        }
        
        return (tokens+newTokens > maxTokens);
    }

    private boolean hasUnlimitedMaxTokens() {
        return maxTokens == UNLIMITED;
    }

    
    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public void setMaxTokens(int max) {
        this.maxTokens = max;
    }

    public void addTokens(int weight) {
        this.tokens += weight;
    }

    public void removeTokens(int weight) {
        this.tokens -= weight;
    }
    
	 /**
     * Aggiunge un Arco in entrata
     * @param arc
     */
    public void addIncoming(Arc arc) {
        this.incoming.add(arc);
    }

  	public void setIncoming(List<Arc> incoming) {
  		this.incoming = incoming;
  	}
    
    public List<Arc> getIncoming() {
  		return incoming;
  	}
    
    /**
     * Aggiunge un Arco in uscita
     * @param arc 
     */
    public void addOutgoing(Arc arc) {
        this.outgoing.add(arc);
    }

  	public void setOutgoing(List<Arc> outgoing) {
  		this.outgoing = outgoing;
  	}

  	public List<Arc> getOutgoing() {
  		return outgoing;
  	}
         
    @Override
    public String toString() {
        return super.toString() + 
               " Tokens=" + this.tokens +
               " max=" + (hasUnlimitedMaxTokens()? "unlimited" : this.maxTokens);
    }
}
