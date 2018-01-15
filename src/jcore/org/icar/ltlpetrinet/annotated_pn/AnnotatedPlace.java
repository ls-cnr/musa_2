package org.icar.ltlpetrinet.annotated_pn;

import petrinet.logic.Place;

public class AnnotatedPlace extends Place {
	
	private PNStateEnum state;

	public AnnotatedPlace(String name, PNStateEnum state) {
		super(name);
		this.state = state;
	}

	public PNStateEnum getState() {
		return state;
	}

	
}
