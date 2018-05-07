package AstFiles;

import java.awt.Color;
import java.util.LinkedList;
import Env.Env;

/**
 * AstFillCircle
 */
public class AstFillCircle extends AstGraphics {

    public AstFillCircle(LinkedList<AstInt> params, Color c) {
        super(params, c);
    }

    public void eval(Env e) throws Exception {
        setColor(e.getGraphics());
        e.getGraphics().fillOval(params.get(0).evalInt(e), params.get(1).evalInt(e), params.get(2).evalInt(e),
                params.get(2).evalInt(e));
    }
}