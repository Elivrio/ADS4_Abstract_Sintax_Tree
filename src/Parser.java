import java.awt.Color;
import java.util.LinkedList;

import AstFiles.*;

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
    if (!reader.hasEnded())
      throw new Exception("Unexpected End of Expression");
  }

  public Ast pNonTerm() throws Exception {
  	Ast a = sNonTerm();
    return a;
  }

  public Ast sNonTerm() throws Exception {
  	AstBlock ab = new AstBlock();
  	while(true) {
		if (reader.hasEnded()) {
			System.out.println("EOF at line " + line);
			return ab;
		}
		if (reader.check(Symbol.END)) {
			System.out.println("End at line " + line);
			line ++;
			reader.eat(Symbol.END);
			return ab;
		}
		Ast a = iNonTerm();
		if (a != null) {
			reader.eat(Symbol.SEMICOLON);
			ab.addInstruction(a);
		}
	} }

  static int line = 0;

  public Ast iNonTerm() throws Exception {
  	line ++;
    String name = "";
    Token t = reader.get();
    Symbol sym = t.getSymbol();LinkedList<AstInt> listInt;
    switch (sym) {
        case CONST:
        	System.out.println("New Const at line " + line);
        	reader.eat(Symbol.CONST);
        	name = ((StringToken)reader.pop()).getString();
        	reader.eat(Symbol.ASSIGN);
        return new AstVar(name, expr(), true);
        case FRECT :
			reader.pop();
			listInt = argList(sym);
        	if(listInt == null) System.out.println("null at line " + line);
        	System.out.println("FillRect at line " + line);
        	return new AstFillRect(listInt, getColor());

		case DRECT :
			reader.pop();
			listInt = argList(sym);
			if(listInt == null) System.out.println("null at line " + line);
			System.out.println("DrawRect at line " + line);

			return new AstDrawRect(listInt, getColor());

        case FCERC :
			reader.pop();
			listInt = argList(sym);
        	if(listInt == null) System.out.println("null at line " + line);
			System.out.println("FillCircle at line " + line);

			return new AstFillCircle(listInt, getColor());

        case DCERC :
			reader.pop();
			listInt = argList(sym);
        	if(listInt == null) System.out.println("null at line " + line);
			System.out.println("DrawCircle at line " + line);
			return new AstDrawCircle(listInt, getColor());

        case BEGIN : {
        	System.out.println("Begin new Block line " + line);
        	reader.eat(Symbol.BEGIN);
          return sNonTerm();
        }
        default :
        	System.out.println("Unrecognised Symbol at line : " + line);
          System.exit(29);
	}
	return null;
  }

  public AstInt expr() throws Exception {
    if (reader.check(Symbol.NRB)) {
    	int i = ((IntToken) reader.pop()).getValue();
    	System.out.println("New int : " + i + " ; at line " + line);
      return new AstInt(i);
    } else if (reader.check(Symbol.VARIA)) {
      return new AstVar(((StringToken)reader.pop()).getString());
    } else if(reader.check(Symbol.END)) {
    	return new AstInt(0);
	}
    if (reader.check(Symbol.LPAR)) {
    reader.eat(Symbol.LPAR);
    AstInt a = expr();
    OpToken operator = ((OpToken)reader.pop(Symbol.OP));
    AstInt b = expr();
    reader.eat(Symbol.RPAR);
    return new AstExpr(operator.getOp(), a, b);
    }
    System.out.println("Null in expr() at line " + line);
    return null;

  }

  public LinkedList<AstInt> argList(Symbol sym) throws Exception {
    LinkedList<AstInt> values = new LinkedList<>();
    reader.eat(Symbol.LPAR);
    values.add(expr());
    reader.eat(Symbol.COMMA);
    values.add(expr());
    reader.eat(Symbol.COMMA);
    values.add(expr());
    reader.eat(Symbol.COMMA);
    if (sym == Symbol.DRECT || sym == Symbol.FRECT) {
      values.add(expr());
      reader.eat(Symbol.COMMA);
    }
    return values;
  }

  public Color getColor() {
  	Color color;
  	try {
		color = ((ColorToken) reader.pop(Symbol.COLOR)).getColor();
		reader.eat(Symbol.RPAR);
	} catch (Exception e) {
  		e.printStackTrace();
  		System.out.println("null color line " + line);
  		return null;
	}
    return color;
  }
}
