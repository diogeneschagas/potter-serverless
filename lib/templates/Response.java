package <%= metadata.project.group %>;

import java.util.Map;

public class <%= metadata.project.name %>Response {

	private final String message;
	private final Map<String, Object> input;

	public <%= metadata.project.name %>Response(String message, Map<String, Object> input) {
		this.message = message;
		this.input = input;
	}

	public String getMessage() {
		return this.message;
	}

	public Map<String, Object> getInput() {
		return this.input;
	}
}