
+!run_testcase_for_conditions
<-
	.print("~~~~~~~~ TESTCASE FOR CONDITIONS ~~~~~~~~");
	!testcase_build_disagreement_pair_list;
	!testcase_get_var_term_substitution;
	!testcase_unification;
	
	!testcase_boolean_formula;
	!testcase_boolean_formula_2;

	!testcase_normalize_condition;
	!testcase_denormalize_condition;
	!testcase_denormalize_condition_2;
	!testcase_denormalize_condition_3;
	!testcase_pretty_print_unifierlist;

	!testcase_implies;
	!testcase_if_only_if;
	!testcase_xor;

	!testcase_convert_bool_formula_to_CNF_2;
	
	!testcase_replace_free_predicate_with_dummy_var;
		
	!testcase_test_condition;
	!testcase_test_condition_2;
	!testcase_test_condition_3;
	!testcase_test_condition_4;
	!testcase_test_condition_5;
	!testcase_test_condition_6;
	!testcase_test_condition_7;
	!testcase_test_condition_8;
	!testcase_test_condition_9;
	!testcase_test_condition_10;
	.print("~~~~~~~~ TEST CONCLUSION ~~~~~~~~");
.

+!testcase_boolean_formula
<-
	!test_boolean_formula(xor([false,true,true]),Bool);
	!assert_plan_output("test_boolean_formula",[
		assert(Bool,false)
	]);
.
+!testcase_boolean_formula_2
<-
	!test_boolean_formula(xor([true,true,true]),Bool);
	!assert_plan_output("test_boolean_formula2",[
		assert(Bool,false)
	]);
.

+!testcase_normalize_condition
<-
	jia.normalize_literal([x,y],and(x,f(a,10,g(x)),or(y,neg(x))),Out);
	!assert_plan_output("normalize_condition",[
		assert(Out,and([var(x),structure(f,[atom(a),const(10),structure(g,[var(x)])]),or([var(y),neg([var(x)])])]))
	]);
.
+!testcase_denormalize_condition
<-
	jia.denormalize_literal(structure(f,[atom(a),const(10),structure(g,[var(x)])]),Out);

 	!assert_plan_output("denormalize_condition",[
		assert(Out,f(a,10,g(x)) )
	]);
.
+!testcase_denormalize_condition_2
<-
	jia.denormalize_literal( and([var(x),var(y)]),Out);

 	!assert_plan_output("denormalize_condition",[
		assert(Out, and(x,y) )
	]);
.
+!testcase_denormalize_condition_3
<-
	jia.denormalize_literal( and([var(x),structure(f,[atom(a),const(10),structure(g,[var(x)])]),or([var(y),neg([var(x)])])]) ,Out);

 	!assert_plan_output("denormalize_condition",[
		assert(Out, and(x,f(a,10,g(x)),or(y,neg(x))) )
	]);
.

+!testcase_pretty_print_unifierlist
<-
	!pretty_print_UnifierList([
		u([s(var(x),atom(a)),s(var(x),atom(c))]),
		u([s(var(x),atom(a)),s(var(x),atom(m))]),
		u([s(var(x),atom(a)),s(var(y),atom(c))]),
		u([s(var(x),atom(a)),s(var(y),atom(c)),s(var(y),atom(m))])
	],String);
	!assert_plan_output("pretty_print_unifierlist",[
		assert(String,"{(x <- a)(x <- c)}  {(x <- a)(x <- m)}  {(x <- a)(y <- c)}  {(x <- a)(y <- c)(y <- m)}  " )
	]);
.

+!testcase_implies
<-	
	Formula = and([ var(1),var(22), or([ var(11) ,neg([var(3)])]) ]);
	!implies_to_CNF(var(1),and([var(3),var(5)]), CNFClauses);
	!assert_plan_output("A implies B",[
		assert(CNFClauses,[[-1,-3,5],[-1,3,-5],[-1,3,5]] )
	]);
.
+!testcase_if_only_if
<-	
	!if_only_if_to_CNF(var(1),and([var(3),var(5)]), CNFClauses);
	!assert_plan_output("A iff B",[
		assert(CNFClauses,[[-1,-3,5],[-1,3,-5],[-1,3,5],[-3,-5,1]] )
	]);
.

+!testcase_xor
<-	
	!xor_to_CNF([var(4),var(5),var(6)], CNFClauses);
	!assert_plan_output("A XOR B",[
		assert(CNFClauses,[[-4,-5,-6],[-4,-5,6],[-4,5,-6],[4,-5,-6],[4,5,6]] )
	]);
.



+!testcase_replace_free_predicate_with_dummy_var
<-
	jia.normalize_literal([x,y],and(f(y,10,g(x)),or(g(y),neg(h(x)))),NormalLogicFormula);
	World=world([f(a,10,g(b)),f(r,10,g(m)),g(t),g(a),h(a),h(b),h(m)]);

	!predicates_of_formula(NormalLogicFormula,PredicateSet);
	!assign_var_to_predicates(PredicateSet,1,PredicateVarMap);

	World=world(WorldContent);
	!replace_free_predicate_with_dummy_var(NormalLogicFormula,WorldContent,PredicateVarMap,BooleanFormula);
	
	!assert_plan_output("replace_free_predicate_with_dummy_var",[
		assert(BooleanFormula,and([var(1),or([var(2),neg([var(3)])])]) )
	]);
.



+!testcase_test_condition
<-
	Condition = condition([x,y],and(f(x,y),g(y)));
	World=world([f(1,2), f(2,3), g(1), g(3)]);
	!test_condition(Condition,World,Bool);
	!assert_plan_output("test_condition",[
		assert(Bool,true)
	]);
.
+!testcase_test_condition_2
<-
	Condition = condition([x,y],and(f(x,y),g(y)));
	World=world([f(1,2), g(1), g(3)]);
	!test_condition(Condition,World,Bool);
	!assert_plan_output("test_condition_2",[
		assert(Bool,false)
	]);
.
+!testcase_test_condition_3
<-
	Condition = condition([x,y],and(h(1),f(x,y),g(y)));
	World=world([f(2,3), g(1), g(3),h(1)]);
	!test_condition(Condition,World,Bool);
	!assert_plan_output("test_condition_3",[
		assert(Bool,true)
	]);
.
+!testcase_test_condition_4
<-
	Condition = condition([],and(h(1),f(2),g(3)));
	World=world([f(2), g(1), g(3),h(1)]);
	!test_condition(Condition,World,Bool);
	!assert_plan_output("test_condition_4",[
		assert(Bool,true)
	]);
.
+!testcase_test_condition_5
<-
	Condition = condition([],and(h(1),f(2),g(3)));
	World=world([f(2), g(1), g(2),h(1)]);
	!test_condition(Condition,World,Bool);
	!assert_plan_output("test_condition_5",[
		assert(Bool,false)
	]);
.
+!testcase_test_condition_6
<-
	!test_condition(condition( and( f(c),f(f) ) ),world([f(c),f(e),f(f)]),Bool);
	!assert_plan_output("test_condition_6",[
		assert(Bool,true)
	]);
.
+!testcase_test_condition_7
<-
	Condition = condition([x],and(f(x),f(v)));
	!test_condition(Condition,world([f(c),f(e),f(r)]),Bool);
	!assert_plan_output("test_condition_7",[
		assert(Bool,false)
	]);
.
+!testcase_test_condition_8
<-
	Condition = condition([x],and(f(x),f(e)));
	!test_condition(Condition,world([f(c),f(e),f(r)]),Bool);
	!assert_plan_output("test_condition_8",[
		assert(Bool,true)
	]);
.
+!testcase_test_condition_9
<-
	Condition = condition([],f(v));
	!test_condition(Condition,world([f(c),f(e),f(f)]),Bool);
	!assert_plan_output("test_condition_9",[
		assert(Bool,false)
	]);
.
+!testcase_test_condition_10
<-
	Condition = condition([],or(f(g),f(e)));
	!test_condition(Condition,world([f(c),f(e),f(f)]),Bool);
	!assert_plan_output("test_condition_10",[
		assert(Bool,true)
	]);
.





+!testcase_build_disagreement_pair_list
<-
	!build_disagreement_pair_list(var(x),atom(a),DisagreementPairList);
	!assert_plan_output("build_disagreement_pair_list",[
		assert(DisagreementPairList,[d(var(x),atom(a))])
	]);

/* 
	!build_disagreement_pair_list(structure(f,[var(x),atom(a)]),structure(f,[atom(b),atom(a)]),DisagreementPairList2);
	!assert_plan_output("build_disagreement_pair_list2",[
		assert(DisagreementPairList2,[d(var(x),atom(b))])
	]);

	!build_disagreement_pair_list(structure(f,[var(x),atom(a)]),structure(f,[atom(b),var(y)]),DisagreementPairList3);
	!assert_plan_output("build_disagreement_pair_list3",[
		assert(DisagreementPairList3,[d(var(y),atom(a)),d(var(x),atom(b))])
	]);


	!build_disagreement_pair_list(structure(f,[var(x),atom(a)]),var(y),DisagreementPairList4);
	!assert_plan_output("build_disagreement_pair_list4",[
		assert(DisagreementPairList4,[d(var(y),structure(f,[var(x),atom(a)]))])
	]);

	!build_disagreement_pair_list(
		structure(f,[atom(a),var(x),structure(g,[var(y)])]),
		structure(f,[var(z),atom(c),structure(g,[var(x)])]),
		DisagreementPairList5
	);
	!assert_plan_output("build_disagreement_pair_list5",[
		assert(DisagreementPairList5,[d(var(y),var(x)),d(var(x),atom(c)),d(var(z),atom(a))])
	]);*/
.

+!testcase_get_var_term_substitution
<-
	!get_var_term_substitution([d(var(y),structure(f,[var(x),atom(a)]))],Substitution);
 	!assert_plan_output("get_var_term_substitution",[
		assert(Substitution,s(var(y),structure(f,[var(x),atom(a)])) )
	]);
.
+!testcase_apply_substitution
<-
	!apply_substitution(
		structure(f,[atom(a),var(x),structure(g,[var(y)])]),
		s(var(x),structure(g,[var(y)])),
		NewTerm
	);
 	!assert_plan_output("apply_substitution",[
		assert(NewTerm,structure(f,[atom(a),structure(g,[var(y)]),structure(g,[var(y)])]) )
	]);
.

+!testcase_unification
<-
	!unification(
		structure(f,[atom(a),var(x),structure(g,[var(y)])]),
		structure(f,[atom(a),atom(b),structure(g,[const(19)])]),
		MGU
	);
	!assert_plan_output("unification: f(a,X,g(Y)) = f(a,b,g(19))",[
		assert(MGU,[s(var(x),atom(b)),s(var(y),const(19))])
	]);

	!unification(
		structure(f,[atom(a),var(z),structure(g,[var(y)])]),
		structure(f,[var(x),atom(b),structure(g,[var(x)])]),
		MGU2
	);
	!assert_plan_output("unification: f(a,Z,g(Y)) = f(X,b,g(X))",[
		assert(MGU2,[s(var(x),atom(a)),s(var(z),atom(b)),s(var(y),atom(a))])
	]);

	!unification(
		structure(f,[var(x1),structure(g,[var(x2),var(x3)]),var(x2),atom(b)]),
		structure(f,[structure(g,[structure(h,[atom(a),var(x5)]),var(x2)]),var(x1),structure(h,[atom(a),var(x4)]),var(x4)]),
		MGU3
	);
	!assert_plan_output("unification: f( X1,g(X2,X3),X2,b ) = f(g(h(a,X5),X2),X1,h(a,X4),X4)",[
		assert(MGU3,[
			s(var(x5),atom(b)),
			s(var(x3),structure(h,[atom(a),atom(b)])),
			s(var(x1),structure(g,[structure(h,[atom(a),atom(b)]),structure(h,[atom(a),atom(b)])])),
			s(var(x2),structure(h,[atom(a),atom(b)])),
			s(var(x4),atom(b))
		])
	]);
.

+!testcase_test_logic_formula
<-
	!test_logic_formula(structure(f,[var(x),var(y)]),world([f(a,b),f(r,t)]),UnifierList,Bool);
	!assert_plan_output("test_logic_formula",[
		assert(Bool,true),
		assert(UnifierList,[
			u([s(var(x),atom(a)),s(var(y),atom(b))]),
			u([s(var(x),atom(r)),s(var(y),atom(t))])
		])
	]);
.
+!testcase_test_logic_formula_2
<-
	!test_logic_formula(structure(f,[var(x),var(y)]),world([f(a),g(r,t)]),UnifierList,Bool);
	!assert_plan_output("test_logic_formula2",[
		assert(Bool,false),
		assert(UnifierList,[])
	]);
.

+!testcase_conjoin_a_couple_of_UnifierList
<-
	!conjoin_a_couple_of_UnifierList([
			u([s(var(x),atom(a)),s(var(y),atom(b))]),
			u([s(var(x),atom(r)),s(var(y),atom(t))])
		],
		[
			u([s(var(z),atom(c)),s(var(y),atom(c))]),
			u([s(var(z),atom(g))])
		],
		UnifierList);
	!assert_plan_output("conjoin_a_couple_of_UnifierList",[
		assert(UnifierList, [
			u([s(var(x),atom(a)),s(var(y),atom(b)),s(var(z),atom(g))]),
			u([s(var(x),atom(r)),s(var(y),atom(t)),s(var(z),atom(g))]),
			u([s(var(x),atom(a)),s(var(y),atom(b)),s(var(y),atom(c)),s(var(z),atom(c))]),
			u([s(var(x),atom(r)),s(var(y),atom(c)),s(var(y),atom(t)),s(var(z),atom(c))])
		])
	]);
	
.

+!testcase_test_logic_formula_3
<-
	!test_logic_formula( 
		structure(f,[var(x)]),
		world([f(a),f(b),g(c)]),
		UnifierList,
		Bool
	);
	!assert_plan_output("test_logic_formula3",[
		assert(Bool,true),
		assert(UnifierList,[
			u([s(var(x),atom(a))]),
			u([s(var(x),atom(b))])
		])
	]);
.

+!testcase_test_logic_formula_4
<-
	!test_logic_formula( 
		and([structure(f,[var(x)]),structure(g,[var(y)])]),
		world([f(a),f(b),g(c)]),
		UnifierList,
		Bool
	);
	!assert_plan_output("test_logic_formula4",[
		assert(Bool,true),
		assert(UnifierList,[
			u([s(var(x),atom(a)),s(var(y),atom(c))]),
			u([s(var(x),atom(b)),s(var(y),atom(c))])
		])
	]);
.

+!testcase_test_logic_formula_5
<-
	!test_logic_formula( 
		or([structure(f,[var(x)]),structure(g,[var(y)])]),
		world([f(a),f(b),g(c)]),
		UnifierList,
		Bool
	);
	!assert_plan_output("test_logic_formula5",[
		assert(Bool,true),
		assert(UnifierList,[
			u([s(var(y),atom(c))]),
			u([s(var(x),atom(a))]),
			u([s(var(x),atom(b))])
		])
	]);
.

+!testcase_test_logic_formula_6
<-
	jia.normalize_literal([x],and(f(x),or(g(x),h(x))),Formula);
	!test_logic_formula( 
		Formula,
		world([f(a),g(a),f(b),h(b)]),
		UnifierList,
		Bool
	);
	.concat("test_logic_formula6: ",Formula,TestName);
	!assert_plan_output(TestName,[
		assert(Bool,true),
		assert(UnifierList,[
			u([s(var(x),atom(a))]),
			u([s(var(x),atom(b))])
		])
	]);
	
.
+!testcase_test_logic_formula_7
<-
	jia.normalize_literal([x,y],or(and(f(x),g(x)),and(h(x,y),g(y))),Formula);
	!test_logic_formula( 
		Formula,
		world([f(a),g(m),g(c),h(a,c)]),
		UnifierList,
		Bool
	);
	.concat("test_logic_formula 7: ",Formula,TestName);
	!assert_plan_output(TestName,[
		assert(Bool,true),
		assert(UnifierList,[
			u([s(var(x),atom(a)),s(var(y),atom(c))])
		])
	]);
.

// detected error: obtained: true
+!testcase_test_logic_formula_8
<-
	jia.normalize_literal([x,y],or(and(f(x),g(x)),and(h(x,y),g(y))),Formula);
	!test_logic_formula( 
		Formula,
		world([f(a),g(m),g(n),h(a,c)]),
		UnifierList,
		Bool
	);
	.concat("test_logic_formula 8: ",Formula,TestName);
	!assert_plan_output(TestName,[
		assert(Bool,false),
		assert(UnifierList,[])
	]);
.

// detected error: [u([])]
+!testcase_test_logic_formula_9
<-
	jia.normalize_literal([],or(and(f(a),g(m)),and(h(a,k),g(n))),Formula);
	!test_logic_formula( 
		Formula,
		world([f(a),g(m),g(n),h(a,c)]),
		UnifierList,
		Bool
	);
	.concat("test_logic_formula 9: ",Formula,TestName);
	!assert_plan_output(TestName,[
		assert(Bool,true),
		assert(UnifierList,[])
	]);
.








	