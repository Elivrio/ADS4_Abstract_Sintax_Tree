package Env;

import java.awt.Graphics2D;
import java.util.HashMap;

import AstFiles.*;

/**
 * Env.Env
 */
public class Env {

    Graphics2D g2;
    HashMap<String, AstVar> vars;


    public Env(Graphics2D g2, HashMap<String, AstVar> vars) {
        this.g2 = g2;
        this.vars = vars;
    }

    public Env(Graphics2D g2) {
    	this(g2, new HashMap<>());
	}

    public Env(Env e) {
    	this.g2 = e.getGraphics();
    	this.vars = e.getVars();
	}

    public Env() {
        this(null, new HashMap<>());
    }

    public Graphics2D getGraphics() {
        return g2;
    }

    public void setGraphics(Graphics2D g2) {
    	this.g2 = g2;
	}


    /**
     * @return the vars
     */
    public HashMap<String, AstVar> getVars() {
        return vars;
    }

    public AstVar getVar(String name) {
        return vars.get(name);
    }

    public void addVar(String name, AstVar var) {
        vars.put(name, var);
    }

    public void setVar(String name, AstVar var) throws Exception {
        if(vars.containsKey(name) && vars.get(name).isConst())
            throw new Exception();
        vars.put(name, var);
    }

    public Env clone() {
    	return new Env(this);
	}
}