import java.io.File;
import java.awt.Graphics;
import java.io.FileReader;
import java.lang.Runnable;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.io.BufferedReader;
import Env.Env;

import AstFiles.*;

public class Main {

  private static void initAndShow(String filename) {
    JFrame frame = new JFrame("ADS4");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.getContentPane().setPreferredSize(new Dimension(800,600));
    frame.getContentPane().add(new MyCanvas(filename));

    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        initAndShow(args[0]);
      }
    });
  }
}

@SuppressWarnings("serial")
class MyCanvas extends JComponent {

  String filename;

  public MyCanvas(String fname) {
    filename = fname;
  }

  @Override
  public void paintComponent(Graphics g) {
	  if (g instanceof Graphics2D) {
		  Graphics2D g2d = (Graphics2D) g;
		  Lexer lexer = null;
		  LookAhead1 look = null;
		  Parser parser = null;
		  Ast ast;

		  try {
			  File fi = new File(filename);
			  FileReader fir = new FileReader(fi);
			  BufferedReader reader = new BufferedReader(fir);
			  lexer = new Lexer(reader);
		  } catch (Exception e) {
			  System.err.println("Error while opening File.");
			  e.printStackTrace();
			  System.exit(1);
		  }

		  try {
			  look = new LookAhead1(lexer);
			  parser = new Parser(look);
		  } catch (Exception e) {
			  System.err.println("Error while opening parser.");
			  e.printStackTrace();
			  System.exit(1);
		  }
		  try {
			  ast = parser.pNonTerm();
		  } catch (Exception e) {
			  System.err.println("Error while creating AST");
			  e.printStackTrace();
			  System.exit(1);
			  return;

		  }

		  // A MODIFIER
		  Env env = new Env(g2d);
		  try {
			  ast.eval(env);
		  } catch (Exception e) {
			  System.err.println("Error while executing AST");
			  e.printStackTrace();
			  System.exit(1);
		  }

		  g2d = env.getGraphics();

		  // A compléter.
		  // Appelez ici votre analyseur et interpréteur, en leur fournissant
		  // l'objet g2d de type Graphics2D. Ils pourront ainsi appeler les fonctions
		  // g2d.drawCircle, g2d.setColor, etc...
		  //
		  // Par exemple :
		  //
		  // File input = new File(filename);
		  // Reader reader = new FileReader(input);
		  // Lexer lexer = new Lexer(reader);
		  // LookAhead1 look = new LookAhead1(lexer);
		  // Parser parser = new Parser(look);
		  // AST ast = parser.progNonTerm();
		  // ast.exec(g2d);

	  }
  }

}
