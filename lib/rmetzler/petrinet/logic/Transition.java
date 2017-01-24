package petrinet.logic;

import java.util.ArrayList;
import java.util.List;

public class Transition
extends PetrinetObject{

    protected Transition(String name) {
        super(name);
    }

    private List<Arc> incoming = new ArrayList<Arc>();
    private List<Arc> outgoing = new ArrayList<Arc>();
    
    /**
     * @return darf die Transition feuern?
     * 
     * La Transizione può scattare?(E' abilitata?)
     */
    public boolean canFire() {
        boolean canFire = true;
        
        // ich denke, dass auch eine Transition, 
        // die nur auf einer Seite Kanten hat, feuern darf
        //
        //Penso che anche una Transizione,
        //che ha un solo Arco potrebbe scattare (allora lo blocco)
        canFire = ! this.isNotConnected();
        
        for (Arc arc : incoming) {
            canFire = canFire & arc.canFire();
        }
        
        for (Arc arc : outgoing) {
            canFire = canFire & arc.canFire();
        }
        return canFire;
    }
    
    /**
     * Transition soll feuern
     * 
     * Scatta Transizione (Consuma i token)
     */
    public void fire() {
        for (Arc arc : incoming) {
            arc.fire();
        }
        
        for (Arc arc : outgoing) {
            arc.fire();
        }
    }
    
    /**
     * @param arc Eingehende Kante hinzufŸgen
     * 
     * Aggiunge un Arco in entrata
     */
    public void addIncoming(Arc arc) {
        this.incoming.add(arc);
    }
    
    /**
     * @param arc ausgehende Kante hinzufŸgen
     * 
     * Aggiunge un Arco in uscita
     */
    public void addOutgoing(Arc arc) {
        this.outgoing.add(arc);
    }

    /**
     * @return ist die Transition mit keiner Kante verbunden?
     * 
     * L'associazione è non associata ad un arco?
     */
    public boolean isNotConnected() {
        return incoming.isEmpty() && outgoing.isEmpty();
    }
    
    public boolean hasMoreThanOneOutgoing() {
    	if( outgoing.size() > 1 )
    		return true;
    	else 
    		return false;
    }
    
    public List<Arc> getIncoming() {
    	return incoming;
    }
    
    public List<Arc> getOutgoing() {
    	return outgoing;
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               (isNotConnected() ? " IS NOT CONNECTED" : "" ) +
               (canFire()? " READY TO FIRE" : "");
    }
    
}
