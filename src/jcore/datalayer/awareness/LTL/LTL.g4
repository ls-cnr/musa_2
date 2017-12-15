
grammar LTL;

@header {
	package layer.awareness.LTL.target;
	import java.util.*;
	/** Class auto-generated using ANTLR */
}

@parser::members {

	private Stack<String> stack = new Stack<>();
	private HashMap<String, Stack<String>> dict = new HashMap<>();
	private Stack<String> tmp;	
		
	public Stack<String> getStack(){
		return stack;
	}
	
	public HashMap<String, Stack<String>> getDict() {
		return dict;
	}
	
	private void put( String s ) {
		if( tmp == null )
			tmp = new Stack<>();
		tmp.push(s);
	}

}

/* Starting Rules */
start
	:		expr
	|		folfor 
	;

folfor
	:		VAR OP arg CL		{ 	String sTmp = $VAR.text + $OP.text + $arg.text + $CL.text;
									stack.push(sTmp);
									put($VAR.text);
									dict.put(sTmp, tmp);
									tmp = null;
								}
	|		OP expr CL
	;
	
arg
	:		VAR COM arg 		{  put($VAR.text); }
	|		VAR					{  put($VAR.text); }
	;

expr
	:		unaOper
	|		binOper
	;
	
unaOper
	:		NOT folfor	{ stack.push("NOT"); }
	|		F folfor 	{ stack.push("F"); }
	|		X folfor	{ stack.push("X"); }
	|		G folfor	{ stack.push("G"); }
	;

binOper
	:		folfor AND folfor	{ stack.push("AND"); }
	|		folfor OR folfor	{ stack.push("OR"); }
	|		folfor IMP folfor	{ stack.push("IMP"); }
	|		folfor BIC folfor	{ stack.push("BIC"); }
	|		folfor U folfor		{ stack.push("U"); }
	|		folfor R folfor		{ stack.push("R"); }
	;

VAR : [a-z]('a'..'z' | 'A'..'Z' | '0'..'9' | '-' | '_' )*  ;

NOT : '!' ;

TRUE : 'true' ;

FALSE : 'false' ;

OP : '(' ;

CL : ')' ;

COM : ',';

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