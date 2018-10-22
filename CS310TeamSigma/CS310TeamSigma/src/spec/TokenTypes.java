package spec;

public enum TokenTypes {
	LET, // let
	ASSIGN, // =
	END_LET, // ;
	QUERY, // query
	END_QUERY, // .
	EQUIVALENCE, // <=>
	IMPLICATION, // ->
	DISJUNCTION, // |
	CONJUNCTION, // &
	NEGATION, // ~
	OPEN_PAREN, // (
	CLOSE_PAREN, // )
	VARIABLE_NAME, // "string"
	TRUE_LITERAL, // true
	FALSE_LITERAL // false
}
