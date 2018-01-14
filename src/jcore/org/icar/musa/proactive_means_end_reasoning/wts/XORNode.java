package org.icar.musa.proactive_means_end_reasoning.wts;

public class XORNode extends OPNode{
	public XORNode(String capability, int score){
		super(capability, score);
	}
	
	public XORNode(String capability, int score, String agent){
		super(capability, score, agent);
	}
}
