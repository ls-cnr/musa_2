package configurationtest;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import configuration.distributed.Sequences;
import configuration.distributed.Solution;

/**
 * 
 * @author Mirko Avantaggiato
 *
 */
public class SequencesTest {

	@Test
	public void test() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		Sequences sc = new Sequences();
		System.out.println("");
		while (true) {
			String input = s.nextLine();
			String[] tmp = input.split(" ");
			if (tmp[0].equals("sol"))
				/*
				 * Set a node as a solution node.
				 */
				sc.processSolution(tmp[1]);
			else if (tmp[0].equals("get")) {
				/*
				 * Print known solutions
				 */
				int i = 0;
				for (Solution sol : sc.getSolutionsSoFar()) {
					System.out.println("Solution " + i++);
					Sequences.printTree(sol.getRoot(), 0);
				}
			} else {
				/*
				 * Add an edge. NAME MUST BE UNIQUE. All nodes with an "X" in
				 * their name are considered XOR
				 */
				String src = tmp[0];
				String dest = tmp[1];
				if (src != dest) {
					sc.processEdge(src, dest, "capabilityName");
					for (ArrayList<String> seqs : sc.getSeqs()) {
						System.out.print(seqs + ", ");
						System.out.println("");
					}
				}
			}
		}
	}

}
