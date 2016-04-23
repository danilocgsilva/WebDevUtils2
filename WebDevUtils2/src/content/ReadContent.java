package content;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to read a content's file.
 * 
 * @author Danilo
 *
 */
public class ReadContent {
	
	/**
	 * Returns a file's content
	 * 
	 * @param file The system file's path.
	 * @return Content file returned provided by file param.
	 * @throws IOException Dealing with external resource requires throwing an exception.
	 */
	public String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}
}
