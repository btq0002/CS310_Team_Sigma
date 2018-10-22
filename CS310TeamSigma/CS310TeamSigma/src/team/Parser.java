package team;

import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import spec.TokenTypes;

public class Parser extends spec.Parser {
	@Override
	public boolean parse(String sentence) {
		sentence = sentence.trim().toLowerCase().replace("let", "").replace("query", "").replace(".", "");
		String[] funcInput = sentence.split(";");
		String query = funcInput[funcInput.length - 1];

		for (int i = 0; i < funcInput.length - 1; i++) {
			for (int k = i + 1; k < funcInput.length; k++) {
				funcInput[k] = letEr(funcInput[k], assigner(funcInput[i]));
			}
			query = letEr(query, assigner(funcInput[i]));
		}
		return querySolver(query);
	}

	private String letEr(String string, String[] assignments) {
		String thing = string.replace(assignments[0], assignments[1]);
		return thing;
	}

	private String[] assigner(String assignments) {
		String[] thing = assignments.replaceAll(" ", "").split("=");
		return thing;
	}

	private boolean querySolver(String input) {
		String[] tokens = input.trim().split(" ");
		if (!input.contains("(")) {
			if (tokens.length == 1) {
				System.out.println(tokens[0]);
				tokens[0] = notFixer(tokens[0]);
				if (tokens[0].equalsIgnoreCase("true"))
					return true;
				else
					return false;
			} else {
				for (int i = 0; i < tokens.length; i++) {
					tokens[i] = notFixer(tokens[i]);
				}
				return boolSolver(tokens);
			}
		} else
			return false;
	}

	private String notFixer(String query) {
		query = query.replaceAll("~true", "false");
		query = query.replaceAll("~false", "true");
		return query;
	}

	private boolean boolSolver(String[] thing) {
		if (thing.length > 3) {
			if (thing[1].trim().equals("->")) {
				String[] solveThing = new String[3];
				for (int i = 0; i < 3; i++) {
					solveThing[i] = thing[thing.length - 3 + i];
				}
				String[] newThing = new String[thing.length - 2];
				newThing[newThing.length - 1] = boolSolverString(solveThing);
				for (int i = 0; i < newThing.length - 1; i++) {
					newThing[i] = thing[i];
				}
				return boolSolver(newThing);
			} else {
				String[] solveThing = new String[3];
				for (int i = 0; i < 3; i++) {
					solveThing[i] = thing[i];
				}
				String[] newThing = new String[thing.length - 2];
				newThing[0] = boolSolverString(solveThing);
				for (int i = 1; i < newThing.length; i++) {
					newThing[i] = thing[i + 2];
				}
				return boolSolver(newThing);
			}
		}
		if (thing[1].equals("<=>")) {
			if (thing[0].equals(thing[2]))
				return true;
			else
				return false;
		} else if (thing[1].equals("&")) {
			if (thing[0].equals("true") && thing[2].equals("true"))
				return true;
			else
				return false;
		} else if (thing[1].equals("|")) {
			if (thing[0].equals("true") || thing[2].equals("true"))
				return true;
			else
				return false;
		} else {
			if (thing[0].equals("true") && thing[2].equals("true"))
				return true;
			else if (thing[0].equals("false") && thing[2].equals("true"))
				return true;
			else if (thing[0].equals("false") && thing[2].equals("false"))
				return true;
			else
				return false;
		}
	}

	private String boolSolverString(String[] thing) {
		if (thing[1].equals("<=>")) {
			if (thing[0].equals(thing[2]))
				return "true";
			else
				return "false";
		} else if (thing[1].equals("&")) {
			if (thing[0].equals("true") && thing[2].equals("true"))
				return "true";
			else
				return "false";
		} else if (thing[1].equals("|")) {
			if (thing[0].equals("true") || thing[2].equals("true"))
				return "true";
			else
				return "false";
		} else {
			if (thing[0].equals("true") && thing[2].equals("true"))
				return "true";
			else if (thing[0].equals("false") && thing[2].equals("true"))
				return "true";
			else if (thing[0].equals("false") && thing[2].equals("false"))
				return "true";
			else
				return "false";
		}
	}

}
