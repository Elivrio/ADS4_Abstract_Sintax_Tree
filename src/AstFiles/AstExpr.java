package AstFiles;

import Env.*;

import static Env.Op.*;

/**
 * AstExpr
 */
public class AstExpr extends AstInt{

    AstInt r, l;
    Op op;
    public AstExpr(Op op, AstInt l, AstInt r) {
        this.op = op;
        this.r = r;
        this.l = l;
    }

    public int evalInt(Env e) throws ArithmeticException {
    	int out = 0;
        try {
			switch (op) {
				case ADD:
					out =  l.evalInt(e) + r.evalInt(e);
				case MUL:
					out =  l.evalInt(e) * r.evalInt(e);
				case SUB:
					return l.evalInt(e) - r.evalInt(e);
				case DIV:
					int right = r.evalInt(e);
					if (right == 0) throw new ArithmeticException();
					out =  l.evalInt(e) / r.evalInt(e);
			}
		} catch (ArithmeticException a) {
        	a.printStackTrace();
		} catch (Exception a) {
        	a.printStackTrace();
		}

		return out;
    }

    public void eval(Env e) {
    	System.out.println("expr : " + evalInt(e));
	}
}