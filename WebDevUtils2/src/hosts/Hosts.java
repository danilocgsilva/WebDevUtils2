package hosts;

import content.ReadContentHandledException;

/**
 * A smart class to deals with hosts OS file, don'r matter if is in Windows or Linux.
 * The comment file's line are discarded.
 * 
 * @author Danilo
 *
 */
public class Hosts {
	
	private String systemOS;
	private String hostPath;
	private String content;
	
	public Hosts() {
		this.systemOS = System.getProperty("os.name");
		if (this.systemOS.toLowerCase().contains("windows")) {
			this.hostPath = "C://Windows//System32//drivers//etc//hosts";
		} else if (this.systemOS.toLowerCase().contains("linux")) {
			this.hostPath = "//etc//hosts";
		} else {
			this.hostPath = "";
		}
		ReadContentHandledException rContent = new ReadContentHandledException();
		this.content = rContent.readFile(this.hostPath);
		
		String eachLine[] = this.content.split("\\r?\\n");
		this.content = "";
		char[] checkChars = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWYXZ1234567890".toCharArray();
		boolean validLine = false;
		
		for (int i = 0; i < eachLine.length; i++) {
			String eachLineLoop = eachLine[i];
			int commentSeparatorPosition = eachLineLoop.indexOf('#');
			if (commentSeparatorPosition != -1) {
				eachLineLoop = eachLineLoop.substring(0, commentSeparatorPosition);
			}
			
			// Verify if the remaining string after # have some relevant content
			for (int j = 0; j < checkChars.length; j++) {
				String charLoop = String.valueOf(checkChars[j]);
				if (eachLineLoop.contains(charLoop)) {
					validLine = true;
				}
			}
			
			if (validLine) {
				this.content += eachLineLoop + "\n";
			}
		}
	}
	
	public String getContent() {
		return this.content;
	}
	
	public String getHostPath() {
		return this.hostPath;
	}
}
