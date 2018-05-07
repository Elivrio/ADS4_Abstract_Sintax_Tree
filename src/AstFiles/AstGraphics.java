package AstFiles;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;

import Env.Env;
/**
 * AstGraphics extends Ast
 */
public class AstGraphics extends Ast {
    Color color;
    LinkedList<AstInt> params;

    public AstGraphics(LinkedList<AstInt> l, Color c) {
    	this.params = l;
    	color = c;
    }

    protected void setColor(Graphics2D g2) {
        if (color != null)
            g2.setColor(color);
    }

    public void eval(Env e) throws Exception {
        setColor(e.getGraphics());
    }
}