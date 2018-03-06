package org.icar.specification.ACLanguage.model;

import org.icar.specification.ACLanguage.CapabilityEntity;

import net.sf.tweety.logics.fol.syntax.RelationalFormula;

public class CapRelationFormula implements CapabilityEntity { //extends CapTerm 
	private RelationalFormula cond;

	public CapRelationFormula(RelationalFormula fol_cond) {
		super();
		this.cond = fol_cond;
	}

	public RelationalFormula getFormula() {
		return cond;
	}

	public void setFormula(RelationalFormula cond) {
		this.cond = cond;
	}

	@Override
	public String toString() {
		return "CapRelationFormula [cond=" + cond + "]";
	}
	
	
	
}
