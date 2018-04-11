package org.icar.testcase;

import static org.junit.Assert.*;

import org.icar.musa.applications.spsreconfiguration.LoadDescriptor;
import org.icar.musa.applications.spsreconfiguration.SPSCircuit;
import org.icar.musa.utils.exception.NotAllowedInAStateOfWorld;
import org.icar.musa.utils.exception.NotAllowedInAnAssumptionSet;
import org.junit.Test;

import net.sf.tweety.lp.asp.parser.ParseException;

public class Circuit_test {

	@Test
	public void test() {
		SPSCircuit circuit = new SPSCircuit();
		circuit.add_connection(1, 2);
		circuit.add_connection(2, 3);
		circuit.add_connection(3, 4);
		circuit.add_connection(4, 5);
		circuit.add_connection(5, 6);
		circuit.add_connection(6, 7);
		circuit.add_connection(7, 8);
		circuit.add_connection(8, 9);
		
		circuit.add_connection(1, 10);
		circuit.add_switcher(10, 11, "swp1");
		circuit.add_connection(11, 12);
		circuit.add_switcher(12, 13, "sws1",true);
		circuit.add_connection(13, 14);
		circuit.add_switcher(2, 15, "swaux1p");
		circuit.add_switcher(15, 16, "swaux1s");
		circuit.add_connection(3, 17);
		circuit.add_switcher(17, 18, "swp2");
		circuit.add_connection(18, 19);
		circuit.add_switcher(19, 20, "sws2",true);
		circuit.add_connection(20, 21);
		circuit.add_switcher(4, 22, "swp3");
		circuit.add_switcher(22, 24, "sws3",true);
		circuit.add_connection(24, 25);
		circuit.add_connection(5, 26);
		circuit.add_switcher(26, 27, "swp4");
		circuit.add_connection(27, 28);
		circuit.add_switcher(28, 29, "sws4",true);
		circuit.add_connection(29, 30);
		circuit.add_switcher(6, 31, "swaux2p");
		circuit.add_switcher(31, 32, "swaux2s");	
		circuit.add_connection(7, 33);
		circuit.add_switcher(33, 34, "swp5");
		circuit.add_connection(34, 35);
		circuit.add_switcher(35, 36, "sws5",true);
		circuit.add_connection(36, 37);
		circuit.add_connection(8, 38);
		circuit.add_switcher(38, 39, "swp6");
		circuit.add_switcher(39, 41, "sws6",true);		
		circuit.add_connection(9, 42);
		circuit.add_switcher(42, 43, "swp7");
		circuit.add_connection(43, 44);
		circuit.add_switcher(44, 45, "sws7",true);
		circuit.add_connection(45, 46);
		circuit.add_connection(14, 16);
		circuit.add_connection(16, 21);
		circuit.add_connection(21, 25);
		circuit.add_connection(25, 30);
		circuit.add_connection(30, 32);
		circuit.add_connection(32, 37);
		circuit.add_connection(37, 41);
		circuit.add_connection(41, 46);

		circuit.add_load(10, 1, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(11, 2, 5, LoadDescriptor.VITAL, 1);
		circuit.add_load(12, 3, 10, LoadDescriptor.SEMIVITAL, 1);
		circuit.add_load(13, 4, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(17, 5, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(18, 6, 5, LoadDescriptor.VITAL, 1);
		circuit.add_load(19, 7, 10, LoadDescriptor.SEMIVITAL, 1);
		circuit.add_load(13, 8, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(22, 9, 5, LoadDescriptor.VITAL, 1);
		circuit.add_load(26, 11, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(27, 12, 5, LoadDescriptor.VITAL, 1);
		circuit.add_load(28, 13, 10, LoadDescriptor.SEMIVITAL, 1);
		circuit.add_load(29, 14, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(33, 15, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(34, 16, 5, LoadDescriptor.VITAL, 1);
		circuit.add_load(35, 17, 10, LoadDescriptor.SEMIVITAL, 1);
		circuit.add_load(36, 18, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(39, 19, 5, LoadDescriptor.VITAL, 1);
		circuit.add_load(42, 21, 5, LoadDescriptor.NONVITAL, 1);
		circuit.add_load(43, 22, 5, LoadDescriptor.VITAL, 1);
		circuit.add_load(44, 23, 10, LoadDescriptor.SEMIVITAL, 1);
		circuit.add_load(45, 24, 5, LoadDescriptor.NONVITAL, 1);
		
		circuit.add_generator(15, "aux1", 20,true);
		circuit.add_generator(24, "mg1", 60);
		circuit.add_generator(31, "aux2", 20,true);
		circuit.add_generator(38, "mg2", 60);
		

		//circuit.log_assumptions();
		//circuit.log_initial_state();
		circuit.log_current_state(circuit.getInitialState());
	}

}
