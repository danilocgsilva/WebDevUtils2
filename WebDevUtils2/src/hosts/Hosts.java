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
		} else {
			this.hostPath = "";
		}
		ReadContentHandledException rContent = new ReadContentHandledException();
		this.content = rContent.readFile(this.hostPath);
	}
	
	public String getContent() {
		return this.content;
	}
}
