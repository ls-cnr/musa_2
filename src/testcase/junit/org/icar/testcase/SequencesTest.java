package org.icar.testcase;

import java.util.ArrayList;
import java.util.Scanner;

import org.icar.musa.solution_extractor.distributed.Sequences;
import org.icar.musa.solution_extractor.distributed.InternalStringSolution;
import org.junit.Test;

/**
 * 
 * @author Mirko Avantaggiato
 *
 */
public class SequencesTest {

	@Test
	public void test() {
		Sequences sc = new Sequences();
		sc.processEdge("w0", "w1", "A","agent_1");
		sc.processEdge("w1", "X", "T","agent_1");
		sc.processEdge("X", "w2", "B","agent_1");
		sc.processEdge("X", "w3", "B","agent_1");
		sc.processEdge("w3", "w4", "C","agent_1");
		sc.processEdge("w2", "w5", "D","agent_1");
		sc.processSolution("w4");
		sc.processSolution("w5");
		
		
		
//		@SuppressWarnings("resource")
//		Scanner s = new Scanner(System.in);
//		System.out.println("");
//		while (true) {
//			String input = s.nextLine();
//			String[] tmp = input.split(" ");
//			if (tmp[0].equals("sol"))
//				/*
//				 * Set a node as a solution node.
//				 */
//				sc.processSolution(tmp[1]);
//			else if (tmp[0].equals("get")) {
//				/*
//				 * Print known solutions
//				 */
//				int i = 0;
//				for (Solution sol : sc.getSolutionsSoFar()) {
//					System.out.println("Solution " + i++);
//					Sequences.printTree(sol.getRoot(), 0);
//				}
//			} else {
//				/*
//				 * Add an edge. NAME MUST BE UNIQUE. All nodes with an "X" in
//				 * their name are considered XOR
//				 */
//				String src = tmp[0];
//				String dest = tmp[1];
//				String cap = tmp[2];
//				if (src != dest) {
//					sc.processEdge(src, dest, cap,"agent_1");
//					for (ArrayList<String> seqs : sc.getSeqs()) {
//						System.out.print(seqs + ", ");
//						System.out.println("");
//					}
//				}
//			}
//		}
	}

}
