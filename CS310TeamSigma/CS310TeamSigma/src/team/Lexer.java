package team;

import java.io.IOException;
import java.io.StringReader;
import spec.TokenTypes;

public class Lexer extends spec.Lexer{

	@Override
	public void initialize(String sentence) {
		input = new StringReader(sentence);
	}
	
	private StringReader input;
	
	@Override
	public void lex() {
		TOKEN = null;

		char character;
		try {
			do {
				character = (char)input.read();

			} while (Character.isWhitespace(character));

			if (character == -1) return;
			else if (character == '=') TOKEN = TokenTypes.ASSIGN;			
			else if (character == ')') TOKEN = TokenTypes.CLOSE_PAREN;
			else if (character == '&') TOKEN = TokenTypes.CONJUNCTION;				
			else if (character == '|') TOKEN = TokenTypes.DISJUNCTION;			
			else if (character == ';') TOKEN = TokenTypes.END_LET;			
			else if (character == '.') TOKEN = TokenTypes.END_QUERY;		
			else if (character == '~') TOKEN = TokenTypes.NEGATION;
			else if (character == '(') TOKEN = TokenTypes.OPEN_PAREN;			
			else if (character == '<') {
				character = (char)input.read();
				if (character == '=') {
					character = (char)input.read();
					if (character == '>') {
						TOKEN = TokenTypes.EQUIVALENCE;
					}
				}
			} else if (character == '-') {
				character = (char)input.read();
				if (character == '>') {
					TOKEN = TokenTypes.IMPLICATION;
				}
			} else if (Character.isLetter(character)) {
				StringBuilder builder = new StringBuilder();
				do {
					builder.append(character);
					input.mark(1);
					character = (char)input.read();
				} while (Character.isLetter(character));

				input.reset();
				String str = builder.toString();

				if (str.equalsIgnoreCase("FALSE")) TOKEN = TokenTypes.FALSE_LITERAL;				
				else if (str.equalsIgnoreCase("LET")) TOKEN = TokenTypes.LET;				
				else if (str.equalsIgnoreCase("QUERY")) TOKEN = TokenTypes.QUERY;				
				else if (str.equalsIgnoreCase("TRUE")) TOKEN = TokenTypes.TRUE_LITERAL;
				else {
					TOKEN = TokenTypes.VARIABLE_NAME;
					LEXEME = str;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
