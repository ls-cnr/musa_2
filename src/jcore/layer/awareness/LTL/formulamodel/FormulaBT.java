package layer.awareness.LTL.formulamodel;

import java.util.Stack;

public class FormulaBT{
    
	private FormulaBTNode root;
	
	private Stack<String> constructionStack;

	public FormulaBT(Stack<String> stack){
    	this.constructionStack = stack;
		this.root = construct();

		this.root = normalize(this.root);
	}
    
    public FormulaBTNode getRoot(){
    	return root;
    }
    
    private FormulaBTNode construct(){
		if(!constructionStack.isEmpty()){
			String tmp = constructionStack.pop();
			FormulaBTNode root = new FormulaBTNode(tmp);
			if( tmp.equals("AND") || tmp.equals("OR") || tmp.equals("IMP") || tmp.equals("BIC") || tmp.equals("U") || tmp.equals("R") ){
				root.addRight(construct());
				root.addLeft(construct());
			}
			else if (tmp.equals("NOT") || tmp.equals("F") || tmp.equals("X") || tmp.equals("G")){
				 root.addLeft(construct());
			}
			return root;
		}
		else
			return null;
	 }
    
    private FormulaBTNode normalize(FormulaBTNode root) {
    	if( !root.getVal().equals("NOT") ){
    		if(root.hasLeft()) root.addLeft(normalize(root.getLeft()));
    		if(root.hasRight()) root.addRight(normalize(root.getRight()));
    	}
    	else
    		root = negateFormula(root.getLeft());
    	return root;
    }
    
    private FormulaBTNode negateFormula( FormulaBTNode root ) {
    	if ( root.getVal().equals("NOT") ){
			root = normalize(root.getLeft());
    	}
		else if (root.getVal().equals("F"))
			root.addLeft( negateFormula(root.getLeft()) );
		else if (root.getVal().equals("G"))
			root.addLeft(negateFormula(root.getLeft()));
		else if (root.getVal().equals("X"))
			root.addLeft(negateFormula(root.getLeft()));
		else if (root.getVal().equals("U")){
			root.setVal("R");
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("R")){
			root.setVal("U");
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("AND")){
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("OR")){
			root.addLeft(negateFormula(root.getLeft()));
			root.addRight(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("IMP")){ // !(a->b) = a && !b
			root.setVal("AND");
			root.addLeft(negateFormula(root.getRight()));
		}
		else if (root.getVal().equals("BIC")){ // !(a<->b) = (a && !b) || (!a && b)
			//TODO
		}
		else if (root.getVal().equals("TRUE"))
			root.setVal("FALSE");
		else if (root.getVal().equals("FALSE"))
			root.setVal("TRUE");
		else
			if( root.getVal().startsWith("!") ){
				root.setVal( root.getVal().substring(1) );
			}
			else
				root.setVal("!"+root.getVal());

		return root;
    }
    
    public void print(FormulaBTNode root) {
    	System.out.print(root.getVal()+"-> ");
    	if( root.hasLeft() ) System.out.print(root.getLeft().getVal());
    	if( root.hasRight() ) System.out.print(root.getRight().getVal());
    	System.out.println("");
    	if(root.hasLeft()) print(root.getLeft());
    	if(root.hasRight()) print(root.getRight());
    }
}
