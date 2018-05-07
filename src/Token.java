import Env.Op;

import java.awt.Color;

class Token {
  protected Symbol sym;

  public Token(Symbol s) {
    sym = s;
  }

  public Symbol getSymbol() {
    return sym;
  }

  public String toString() {
    return ("Symbol : " + this.sym);
  }
}

class OpToken extends Token {
  protected String operator;

  public OpToken(String ope) {
    super(Symbol.OP);
    operator = ope;
  }

  public int apply(int a, int b) throws Exception {
    switch (operator) {
      case "+" : return (a + b);
      case "-" : return (a - b);
      case "/" : return (a / b);
      case "*" : return (a * b);
      default : throw new Exception("Should Not Happen : Missmatched Operator");
    }
  }

  public Op getOp() throws Exception {
    switch (operator) {
    case "+":
      return Op.ADD;
    case "-":
      return Op.SUB;
    case "/":
      return Op.DIV;
    case "*":
      return Op.MUL;
    default:
      throw new Exception("Should Not Happen : Missmatched Operator");
    }
  }

  public String toString() {
    return (super.toString() + " " + operator);
  }
}

class IntToken extends Token {
  protected int value;

  public IntToken(int i) {
    super(Symbol.NRB);
    value = i;
  }

  public int getValue() {
    return value;
  }


  public String toString() {
    return (super.toString() + " " + value);
  }
}

class ColorToken extends Token {
  protected Color color;

  public ColorToken(String i) {
    super(Symbol.COLOR);
    int r = Integer.parseInt(i.substring(1,3), 16);
    int g = Integer.parseInt(i.substring(3,5), 16);
    int b = Integer.parseInt(i.substring(5), 16);
    color = new Color(r, g, b);
  }

  public Color getColor() {
    return color;
  }

  public String toString() {
    return (super.toString() + " " + color.toString());
  }
}

class StringToken extends Token {
  protected String str;

  public StringToken(String str) {
    super(Symbol.VARIA);
    this.str = str;
  }

  public String getString() {
    return str;
  }

  public String toString() {
    return (super.toString() + " " + str);
  }
}
