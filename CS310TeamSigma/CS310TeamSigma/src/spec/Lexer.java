package spec;

public abstract class Lexer {
	public String LEXEME;
	public TokenTypes TOKEN = null;
	
	public abstract void initialize(String sentence);
	public abstract void lex();
}