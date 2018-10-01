package spec;

public abstract class Parser {
	public Lexer lexer;
	
	public abstract boolean parse(String sentence);
	
	// OPTIONAL DEBUG FRAMEWORK FOLLOWS
	
	protected int debug_indent;
	
	protected void debug_start() {
		debug_indent = 0;
	}
	
	protected void debug_open(String production) {
		debug_flush(production + " {");
		debug_indent++;
	}
	
	protected void debug_close(String production) {
		debug_close(production, null);
	}
	
	protected void debug_close(String production, Object result) {
		debug_flush("Return: " + (result != null ? result.toString() : "void"));
		debug_flush("End of " + production);
		debug_indent--;
		debug_flush("}");
	}
	
	protected void debug_lexed() {
		debug_flush("Lexed: " + lexer.TOKEN + (lexer.LEXEME != null ? "[" + lexer.LEXEME + "]" : ""));
	}
	
	protected void debug_flush(String line) {
		for (int i = 1; i <= debug_indent; i++)
			System.out.print("  ");
		System.out.println(line);
	}
}