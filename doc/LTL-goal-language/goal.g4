/**
 * Define a grammar called Hello
 */
grammar goal;

@header {
package org.icar.specification.LTLgoal;
}

goal_model: 'goalmodel' '{' ltl_extended_list '}';

ltl_extended_list
	:
	|	single_goal
	|	single_goal ltl_extended_list
	;

single_goal
	:	'goal' atom '=' ltl_extended '.'
	|  	'goal' atom '=' ltl_extended goalpriority '.' 
	;

goalpriority : '[' numeral ']'; 

ltl_extended : formula | quantified_formula;

quantified_formula 
	: 	'foreach' var_list ':' formula 	# UniversalQuantifier
	|	'exists' var_list ':' formula	# ExistentialQuantifier
;

formula 
	: 	'true' 				# AlwaysTrue
	| 	'false' 			# AlwaysFalse
	| 	predicate 			# APredicate
	| 	unary_operator 		# UnaryOperator
	| 	bracketed 			# FormulaWithBracket
	;

bracketed 
	: 	'(' formula ')' 
	| 	'(' binary_operator ')'
	;

unary_operator
	:		'not' formula	# NotUnaryOperator
	|		'F' formula		# FinallyUnaryOperator
	|		'X' formula		# NextUnaryOperator
	|		'G' formula		# GloballyUnaryOperator
	;

binary_operator
	:		formula 'and' formula	# AndBinaryOperator
	|		formula 'or' formula	# OrBinaryOperator
	|		formula '->' formula	# IfBinaryOperator
	|		formula '<->' formula	# IffBinaryOperator
	|		formula 'U' formula		# UntilBinaryOperator
	|		formula 'R' formula		# ReleaseBinaryOperator
	;


/* from prolog */
predicate 
    : atom | atom '(' termlist ')'
    ;

termlist 
    : 
    | term | termlist ',' term
    ;

term 
    : numeral | variable | predicate | STRING
    ;


atom 
    : ATOMID
    ;

var_list : variable | variable ',' var_list;

variable 
    : VARIABLEID
    ;

numeral 
    : FLOAT
    ;

FLOAT
	: DIGIT+ '.' DIGIT* 		// match 1. 39. 3.14159 etc... 
	| '.' DIGIT+ 				// match .1 .14159
	;
	
VARIABLEID
	: UCLETTER (LCLETTER|UCLETTER|DIGIT)*
	;
	
ATOMID
	: LCLETTER (LCLETTER|UCLETTER|DIGIT)*
	;

STRING: '"' (ESC|.)*? '"' ;


ESC : '\\"' | '\\\\' ;

LCLETTER : [a-z_];

UCLETTER : [A-Z];

DIGIT : [0-9];

COMMENT : '/*' .*? '*/' -> skip ;
WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

