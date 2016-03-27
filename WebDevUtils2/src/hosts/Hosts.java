package hosts;

import content.ReadContentHandledException;

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
		
		for (int i = 0; i < eachLine.length; i++) {
			String eachLineLoop = eachLine[i];
			int commentSeparatorPosition = eachLineLoop.indexOf('#');
			if (commentSeparatorPosition != -1) {
				eachLineLoop = eachLineLoop.substring(0, commentSeparatorPosition);
			}
			
			this.content += eachLineLoop + "\n";
		}
	}
	
	public String getContent() {
		return this.content;
	}
}
