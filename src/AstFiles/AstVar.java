package AstFiles;

import Env.Env;
/**
 * AstVar extends Ast
 */
public class AstVar extends AstInt {

    String name;
    boolean cst;
    AstInt val;

    public AstVar(String name, AstInt val, Boolean cst) {
        this.val = val;
        this.cst = cst;
        this.name = name;
    }

    public AstVar(String name) {
        this.name = name;
    }

    public int evalInt(Env e) throws Exception {
        if(name == null) throw new NullPointerException();
        if(val != null) {
            e.setVar(name, this);
        }
        return e.getVar(name).getValue(e);
    }

    public void eval(Env e) {
    	try {
			System.out.println("var : " + evalInt(e));
		} catch (Exception ex) {
    		ex.printStackTrace();
		}
	}

    public int getValue(Env e) throws Exception {
        return val.evalInt(e);
    }

    public boolean isConst() {
        return cst;
    }
}