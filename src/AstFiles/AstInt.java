package AstFiles;

import Env.Env;

/**
 * AstInt
 */
public class AstInt extends Ast{
    int i;

    public AstInt(int val) {
        this.i = val;
    }

    public AstInt() {
        this.i = 0;
    }

    public int evalInt(Env e) throws Exception {
    	eval(e);
        return i;
    }

    public void eval (Env e) {
    	System.out.println("int : " + i);
	}
}