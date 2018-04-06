package org.icar.linear_temporal_logic.annotated_petrinet;

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
