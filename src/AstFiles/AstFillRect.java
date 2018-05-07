package AstFiles;

import java.awt.Color;
import java.util.LinkedList;

import Env.Env;

/**
 * AstFillRect
 */
public class AstFillRect extends AstGraphics{

    public AstFillRect(LinkedList<AstInt> params, Color c) {
        super(params, c);
    }

    public void eval(Env e) throws Exception{
    	System.out.println("size : " + params.size());
        setColor(e.getGraphics());
        if(e.getGraphics() == null) System.out.println("null graphics");
		e.getGraphics().fillRect(params.get(0).evalInt(e), params.get(1).evalInt(e), params.get(2).evalInt(e), params.get(3).evalInt(e));
    }
}