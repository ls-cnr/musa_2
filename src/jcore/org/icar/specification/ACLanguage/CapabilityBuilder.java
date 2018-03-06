package org.icar.specification.ACLanguage;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.icar.musa.core.context.evolution.EvolutionOperator;
import org.icar.musa.core.context.evolution.EvolutionScenario;
import org.icar.musa.core.fol_reasoner.FOLCondition;
import org.icar.musa.core.runtime_entity.AbstractCapability;
import org.icar.musa.core.runtime_entity.CapabilityEvolutionScenario;
import org.icar.specification.ACLanguage.model.CapEvoAction;
import org.icar.specification.ACLanguage.model.CapEvoActionList;
import org.icar.specification.ACLanguage.model.CapEvoScenario;
import org.icar.specification.ACLanguage.model.Capability;

import net.sf.tweety.logics.fol.syntax.FolFormula;

public class CapabilityBuilder {
	public static Capability parse(InputStream stream) throws IOException {
		CharStream in = CharStreams.fromStream(stream);

		return convert_stream_into_capability(in);
 	}

	public static Capability parse(String goal_string) throws IOException {
		CharStream in = CharStreams.fromReader(new StringReader(goal_string));

		return convert_stream_into_capability(in);
 	}
	
	public static AbstractCapability convertToAbstract(Capability cap) {
		List<EvolutionScenario> evolution_set = new LinkedList<EvolutionScenario>();
		for (CapEvoScenario scen : cap.getEvo().getScenarios()) {
			CapabilityEvolutionScenario scenario = new CapabilityEvolutionScenario(scen.getName());
			
			CapEvoActionList opset = scen.getActions();
			for (CapEvoAction a : opset.getActions()) {
				scenario.addOperator((EvolutionOperator) a);
			}
			evolution_set.add(scenario);
		}
		FOLCondition pre = new FOLCondition((FolFormula) cap.getPre().getFormula());
		FOLCondition post = new FOLCondition((FolFormula) cap.getPost().getFormula());
		AbstractCapability capability = new AbstractCapability(cap.getName(), evolution_set, pre, post);
		
		return capability;
	}

	/**
	 * @param in The stream that contains the goal model description
	 * @return Internal representation of the goal model
	 */
	private static Capability convert_stream_into_capability(CharStream in) {
		// create a lexer that feeds off of input CharStream
		capabilityLexer lexer = new capabilityLexer(in);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// create a parser that feeds off the tokens buffer
		capabilityParser parser = new capabilityParser(tokens);
		
		myCapabilityVisitor my_visitor = new myCapabilityVisitor();
		return (Capability) my_visitor.visit(parser.capability());
	}

}
