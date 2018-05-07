package AstFiles;

import java.util.LinkedList;
import Env.Env;
/**
 * AstBlock extends Ast
 */
public class AstBlock extends Ast {

    LinkedList<Ast> seq;

    // public AstBlock(AstBlock parent, LinkedList<Ast> seq) {
    //     this.seq = seq;
    //     this.parent = parent;
    // }

    public AstBlock(LinkedList<Ast> seq) {
        this.seq = seq;
    }
    public AstBlock() {
    	this.seq = new LinkedList<>();
	}

    public void eval(Env e) throws Exception {
        Env sub = e.clone();
        for (Ast ast : seq)
            ast.eval(sub);
    }

    public void addInstruction(Ast a) {
    	this.seq.add(a);
	}
}