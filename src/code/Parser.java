package src.code;

import java.awt.Color;
import java.util.LinkedList;

class Parser {
  protected LookAhead1 reader;
  /*
  P -->     S

  S -->     I; S
            | None

  I -->     Begin S End
            | If expr Then I Else I
            | DrawCircle(expr, expr, expr, color)
            | FillCircle(expr, expr, expr, color)
            | DrawRect(expr, expr, expr, expr, color)
            | FillRect(expr, expr, expr, expr, color)
            | Const ident = expr
            | Var ident = expr
            | ident = expr

    expr --> nombre
            | ( expr operator expr )
            | ident
  */
  public Parser(LookAhead1 l) {
      reader = l;
  }

  public void evalExpr() throws Exception{
    pNonTerm();
    if (reader.hasEnded() == false)
      throw new Exception("Unexpected End of Expression");
  }

  public Ast pNonTerm() throws Exception {
    Ast a = sNonTerm();
    return a;
  }

  public Ast sNonTerm() throws Exception {
    if (reader.hasEnded())
      return null;
    Ast a = iNonTerm();
    if (a != null) {
      reader.eat(Symbol.SEMICOLON);
      a.setNext(sNonTerm());
    }
    return a;
  }

  public Ast iNonTerm() throws Exception {
    Ast a = null;
    String name = "";
    Token t = reader.get();
    Symbol sym = t.getSymbol();
    while (true) {
      switch (sym) {
        case FRECT :
          name = "fillRect";
          sym = Symbol.RECT;
          break;
        case DRECT :
          name = "drawRect";
          sym = Symbol.RECT;
          break;
        case FCERC :
          name = "fillCircle";
          sym = Symbol.CERC;
          break;
        case DCERC :
          name = "drawCircle";
          sym = Symbol.CERC;
          break;
        case CERC :
        case RECT : {
          reader.pop();
          LinkedList<Integer> values = new LinkedList<Integer>();
          reader.eat(Symbol.LPAR);
          values.add(expr());
          reader.eat(Symbol.COMMA);
          values.add(expr());
          reader.eat(Symbol.COMMA);
          values.add(expr());
          reader.eat(Symbol.COMMA);
          if (sym == Symbol.RECT) {
            values.add(expr());
            reader.eat(Symbol.COMMA);
          }

          Color color = ((ColorToken)reader.pop(Symbol.COLOR)).getColor();
          reader.eat(Symbol.RPAR);
          return new Ast(name, null, values, color);
        }
        case BEGIN : {
          reader.pop();
          Ast block = sNonTerm();
          name = "Begin";
          reader.eat(Symbol.END);
          a = new Ast(name, block);
          return a;
        }
        default :
          return a;
      }
    }
  }

  public int expr() throws Exception {
    if (reader.check(Symbol.NRB)) {
      return ((IntToken)reader.pop()).getValue();
    }
    //if (reader.check(Symbol.LPAR)) {
    reader.eat(Symbol.LPAR);
    int a = expr();
    OpToken operator = ((OpToken)reader.pop(Symbol.OP));
    int b = expr();
    reader.eat(Symbol.RPAR);
    return operator.apply(a, b);
    //}

  }
}
