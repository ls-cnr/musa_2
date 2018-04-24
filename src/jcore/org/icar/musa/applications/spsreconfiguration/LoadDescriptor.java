package org.icar.musa.applications.spsreconfiguration;

import java.util.Comparator;

import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;

import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class LoadDescriptor {
	public static final int VITAL=0;
	public static final int SEMIVITAL=1;
	public static final int NONVITAL=2;
	
	private int source;
	private int id;
	private int pow;
	private int type;
	private int priority;
	
	private boolean open;
	
	public LoadDescriptor(int source, int id, int pow, int type, int priority) {
		super();
		this.source = source;
		
		this.id = id;
		this.pow = pow;
		this.type = type;
		this.priority = priority;
		
		this.open = false;
	}
	public LoadDescriptor(int source, int id, int pow, int type, int priority,boolean open) {
		super();
		this.source = source;
		
		this.id = id;
		this.pow = pow;
		this.type = type;
		this.priority = priority;

		this.open = open;
	}

	public String getName() {
		return "l"+id;
	}

	public int getSourceId() {
		return source;
	}

	public String getSourceName() {
		return "n"+source;
	}

	public int getPow() {
		return pow;
	}

	public void setPow(int pow) {
		this.pow = pow;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getSwitcher() {
		return "sw_"+id;
	}
	
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}

	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public FOLCondition getLoadCondition() {
		return new FOLCondition(new DLPAtom("on",new Constant (getName())));
	}

	public static Comparator<? super LoadDescriptor> getComparator() {
		Comparator<LoadDescriptor> comp = new Comparator<LoadDescriptor>(){
			@Override
			public int compare(LoadDescriptor e1, LoadDescriptor e2){
				if (e1.type < e2.type)
					return -1;
				else if (e1.type > e2.type)
					return 1;
				else {
					if (e1.priority<e2.priority)
						return -1;
					else if (e1.priority>e2.priority)
						return 1;
					else
						return 0;
				}
				
			}
		};
		return comp;
	}
	
}
