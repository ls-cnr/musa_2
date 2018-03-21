package org.icar.musa.core.runtime_entity;

import org.icar.musa.core.RunTimeEntity;
import org.icar.musa.core.context.StateOfWorld;

public abstract class QualityAsset implements RunTimeEntity {
	public abstract long max_score();
	public abstract long evaluate_state(StateOfWorld w);
}
