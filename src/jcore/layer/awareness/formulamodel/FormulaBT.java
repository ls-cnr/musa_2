package layer.awareness.formulamodel;

import java.util.Stack;

public class FormulaBT{
    
	private FormulaBTNode root;
	
	private Stack<String> constructionStack;

	public FormulaBT(Stack<String> stack){
    	this.constructionStack = stack;
		this.root = construct();
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
			normalization();
			return root;
		}
		else
			return null;
	 }
    
    private void normalization() {
    	//TODO
    }
}
