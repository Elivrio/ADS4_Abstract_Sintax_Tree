package AstFiles;

import Env.Env;

abstract public class Ast {

  // private AstBlock parentBlock;

  // public Ast(Ast parentBlock) {
  //   this.parentBlock = parentBlock;
  // }

  public Ast() { }

  // public void setNext(Ast next) {
  //   this.next = next;
  // }

  abstract public void eval(Env e) throws Exception;
  // {
  //   if (color != null)
  //     g2.setColor(color);
  //   switch(fname) {
  //     case "fillRect" : g2.fillRect(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(3)); break;
  //     case "drawRect" : g2.drawRect(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(3)); break;
  //     case "fillCircle" : g2.fillOval(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(2)); break;
  //     case "drawCircle" :  g2.drawOval(expressions.get(0), expressions.get(1), expressions.get(2), expressions.get(2)); break;
  //   }
  //   if (block != null) {
  //     block.exe(g2);
  //   }
  //   if (next != null) {
  //     next.exe(g2);
  //   }
  // }
}
