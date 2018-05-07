package AstFiles;

import java.awt.Color;
import java.util.LinkedList;

import Env.Env;


/**
 * AstDrawRect
 */
public class AstDrawRect extends AstGraphics{

    public AstDrawRect(LinkedList<AstInt> params, Color c) {
        super(params, c);
    }

    public void eval(Env e) throws Exception {
        setColor(e.getGraphics());
        e.getGraphics().drawRect(params.get(0).evalInt(e), params.get(1).evalInt(e), params.get(2).evalInt(e), params.get(3).evalInt(e));
    }
}