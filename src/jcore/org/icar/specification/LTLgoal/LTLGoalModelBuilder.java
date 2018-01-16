package org.icar.specification.LTLgoal;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.icar.specification.LTLgoal.model.GoalModel;

public class LTLGoalModelBuilder {
	
	public static GoalModel parse(InputStream stream) throws IOException {
		CharStream in = CharStreams.fromStream(stream);

		return convert_stream_into_goalmodel(in);
 	}

	public static GoalModel parse(String goal_string) throws IOException {
		CharStream in = CharStreams.fromReader(new StringReader(goal_string));

		return convert_stream_into_goalmodel(in);
 	}

	/**
	 * @param in The stream that contains the goal model description
	 * @return Internal representation of the goal model
	 */
	private static GoalModel convert_stream_into_goalmodel(CharStream in) {
		// create a lexer that feeds off of input CharStream
		goalLexer lexer = new goalLexer(in);

		// create a buffer of tokens pulled from the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// create a parser that feeds off the tokens buffer
		goalParser parser = new goalParser(tokens);
		
		myGoalVisitor my_visitor = new myGoalVisitor();
		return (GoalModel) my_visitor.visit(parser.goal_model());
	}
	

}
