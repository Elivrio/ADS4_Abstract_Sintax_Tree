package src.code;

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;

class Ast {
  private String fname; // La "fonction"
  private Ast block;
  private Ast next; // La suite du programme
  private LinkedList<Integer> expressions; // Les entiers nécessaires au calcul
  private Color color; // La couleur nécessaire au calcul.


  public Ast(String fname, Ast next, LinkedList<Integer> expressions, Color color) {
    this.fname = fname;
    this.next = next;
    this.expressions = expressions;
    this.color = color;
  }

  public Ast(String fname, Ast block) {
    this.fname = fname;
    this.block = block;
    this.expressions = null;
    this.color = null;
  }

  public void setNext(Ast next) {
    this.next = next;
  }

  public void exe(Graphics2D g2) {
    if (color != null)
      g2.setColor(color);
    switch(fname) {
      case "fillRect" : g2.fillRect(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(3)); break;
      case "drawRect" : g2.drawRect(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(3)); break;
      case "fillCircle" : g2.fillOval(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(2)); break;
      case "drawCircle" :  g2.drawOval(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(2)); break;
    }
    if (block != null) {
      block.exe(g2);
    }
    if (next != null) {
      next.exe(g2);
    }
  }

  public String toString() {
    return this.fname + " " + ((next != null)?next.toString():"");
  }
}
