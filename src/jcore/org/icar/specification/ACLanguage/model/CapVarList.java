package org.icar.specification.ACLanguage.model;

import java.util.HashSet;
import java.util.Set;

import org.icar.specification.ACLanguage.CapabilityEntity;

import net.sf.tweety.logics.commons.syntax.Variable;

public class CapVarList implements CapabilityEntity {
	private Set<Variable> var_list;

	public CapVarList() {
		super();
		var_list = new HashSet<Variable>();
	}

	public Set<Variable> getVar_list() {
		return var_list;
	}
	
	
}
