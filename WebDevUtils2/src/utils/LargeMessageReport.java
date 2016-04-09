package utils;

public class LargeMessageReport {
	public void printMessage(String message) {
		String lineSeparator = System.getProperty("line.separator");
		String lines[] = message.split(lineSeparator);
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}
	}
}
