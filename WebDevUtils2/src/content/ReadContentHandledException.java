package content;

import java.io.IOException;

/**
 * Reads a file's content. Manage error internally.
 * 
 * @author Danilo
 *
 */
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
