
grammar LTL;

@header {
	package layer.awareness.LTL.target;
	import java.util.*;
}

@parser::members {

	private Stack<String> stack = new Stack<>();
		
	public Stack<String> getStack(){
		return stack;
	}

}

/* Starting Rules */
start
	:		expr
	|		atom 
	;

atom
	:		VAR		{ stack.push($VAR.text); }
	|		TRUE	{ stack.push("TRUE"); }
	|		FALSE	{ stack.push("FALSE"); }
	|		OP expr CL
	;

expr
	:		unaOper
	|		binOper
	;
	
unaOper
	:		NOT atom{ stack.push("NOT"); }
	|		F atom 	{ stack.push("F"); }
	|		X atom	{ stack.push("X"); }
	|		G atom	{ stack.push("G"); }
	;

binOper
	:		atom AND atom	{ stack.push("AND"); }
	|		atom OR atom	{ stack.push("OR"); }
	|		atom IMP atom	{ stack.push("IMP"); }
	|		atom BIC atom	{ stack.push("BIC"); }
	|		atom U atom		{ stack.push("U"); }
	|		atom R atom		{ stack.push("R"); }
	;

VAR : [a-z][a-zA-Z]*  ;

NOT : '!' ;

TRUE : 'true' ;

FALSE : 'false' ;

OP : '(' ;

CL : ')' ;

G : 'G' ;

F : 'F' ;

X : 'X' ;

U : 'U' ;

R : 'R' ;

AND : '&&' ;

OR : '||' ;

IMP : '->' ;

BIC : '<->' ;

WS : [ \t\r]+ -> skip ; // skip spaces, tabs, newlines

NEWLINE: '\r'? '\n' ;