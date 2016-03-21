package content;

import java.io.IOException;

public class ReadContentHandledException {
	public String readFile(String file) {
		ReadContent rContent = new ReadContent();
		String content = null;
		try {
			content = rContent.readFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
}
