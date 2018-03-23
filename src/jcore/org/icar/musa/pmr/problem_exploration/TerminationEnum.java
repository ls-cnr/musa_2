package org.icar.musa.pmr.problem_exploration;

/*
 * MAX_TIME: the algorithm terminates after a number of ms: the most scored nodes are "exit" nodes
 * MAX_ITERATIONS: the algorithm terminates after a number of iterations: the most scored nodes are "exit" nodes
 * SCORE_THRESHOLD: the algorithm terminates when at least 5 nodes have a score that is higher than a threshold: these are the "exit" nodes
 * FULL_GOAL_ACHIEVEMENT: the algorithm terminates when at least 5 nodes reach a full goal achievement: these are the "exit" nodes
 */
public enum TerminationEnum {
	MAX_TIME, MAX_ITERATIONS, SCORE_THRESHOLD, FULL_GOAL_ACHIEVEMENT;
}
