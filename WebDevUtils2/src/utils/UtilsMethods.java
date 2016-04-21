package utils;

public class UtilsMethods {
	
	public void multiLineConsoleMessage(String message) {
		String lineSeparator = System.getProperty("line.separator");
		String lines[] = message.split(lineSeparator);
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}
	}
	
	public void singleLineConsoleMessage(String message) {
		System.out.println(message);
	}
	
	public void separator() {
		System.out.println("---");
	}
	
}
