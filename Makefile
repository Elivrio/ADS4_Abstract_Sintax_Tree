.DEFAULT_GOAL := run
NAME = 'projet.jar'
PATHMAIN = 'src/code/Main.java'
PATHMANIFEST = 'src.code.Main'
MANIFEST = 'MANIFEST.MF'
BINFOLDER = 'bin/'
LEX = 'src/code/lex.flex'
SIMPLE = 'src/tests/simple'
SIMPLE2 = 'src/tests/simple2'
SIMPLEAR = 'src/tests/simple_arithmetic'
CONDITIONALS = 'src/tests/conditionals'
CONSTANTSB = 'src/tests/constants_bad'
CONSTANTS1 = 'src/tests/constants_good'
CONSTANTS2 = 'src/tests/constants_good2'
VARIABLES1 = 'src/tests/variables_good'
VARIABLES2 = 'src/tests/variables_good2'
VARIABLES3 = 'src/tests/variables_good3'
VARIABLESB1 = 'src/tests/variables_bad'
VARIABLESB2 = 'src/tests/variables_bad2'

all :
	@mkdir -p $(BINFOLDER)
	@javac -d $(BINFOLDER) $(PATHMAIN)
	@echo 'Main-class: '$(PATHMANIFEST) > $(MANIFEST)
	@jar -cvmf $(MANIFEST) $(NAME) -C $(BINFOLDER) ./ >/dev/null

clean :
	@rm -rf $(BINFOLDER) $(MANIFEST)

lexclean :
	@rm -f 'src/code/Lexer.Java'

fclean : clean lexclean
	@rm -f $(NAME)

compile : lexclean
	@jflex $(LEX)

run : fclean compile all

simple : run
	@java -jar $(NAME) $(SIMPLE)

simple2 : run
	@java -jar $(NAME) $(SIMPLE2)

simplear : run
	@java -jar $(NAME) $(SIMPLEAR)

conditionals : run
	@java -jar $(NAME) $(CONDITIONALS)

cst1 :
	@java -jar $(NAME) $(CONSTANTS1)

cst2 :
	@java -jar $(NAME) $(CONSTANTS2)

cstb1 :
	@java -jar $(NAME) $(CONSTANTSB)

var1 :
	@java -jar $(NAME) $(VARIABLES1)

var2 :
	@java -jar $(NAME) $(VARIABLES2)

var3 :
	@java -jar $(NAME) $(VARIABLES3)

varB1 :
	@java -jar $(NAME) $(VARIABLESB1)

varB2 :
	@java -jar $(NAME) $(VARIABLESB2)
