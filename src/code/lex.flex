package src.code;

%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%yylexthrow LexerException

%{
public class LexerException extends Exception {

  public LexerException () {
    super("Lexer Exception, unexpected caracter found at line " + yyline + " and column " + yycolumn + ".");
  }
}
%}

number = [0-9]*
hex = [0-9A-F]
color = #{hex}{hex}{hex}{hex}{hex}{hex}
operator = "+" | "-" | "/" | "*"
identifier = [a-zA-Z_]*
blank = "\n" | "\r" | " " | "\t"

%%
"FillRect"      {return new Token(Symbol.FRECT);}
"DrawRect"      {return new Token(Symbol.DRECT);}
"FillCircle"    {return new Token(Symbol.FCERC);}
"DrawCircle"    {return new Token(Symbol.DCERC);}
"("             {return new Token(Symbol.LPAR);}
")"             {return new Token(Symbol.RPAR);}
","             {return new Token(Symbol.COMMA);}
";"             {return new Token(Symbol.SEMICOLON);}
"Begin"         {return new Token(Symbol.BEGIN);}
"End"           {return new Token(Symbol.END);}
"If"            {return new Token(Symbol.IF);}
"Then"          {return new Token(Symbol.THEN);}
"Else"          {return new Token(Symbol.ELSE);}
"+"             {return new OpToken("+");}
"-"             {return new OpToken("-");}
"/"             {return new OpToken("/");}
"*"             {return new OpToken("*");}
{color}         {return new ColorToken(yytext());}
{number}        {return new IntToken(Integer.parseInt(yytext()));}
{blank}         {}
[^]             {throw new LexerException();}
