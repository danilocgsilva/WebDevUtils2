import FTP.FTPDownload;
import content.ReadContentHandledException;
import hosts.Hosts;

public class WebDevUtils2 {

	public static void main(String[] args) {
	
/*		String content = null;
		ReadContentHandledException rContent = new ReadContentHandledException();
		content = rContent.readFile("D://Users//Danilo//Temporários//cteste.txt");
		System.out.println(content);	*/
		
		/*Hosts hosts = new Hosts();
		System.out.println(hosts.getContent());*/
		
		FTPDownload ftpd = new FTPDownload();
		ftpd.setHost("www.danilocgsilva.me");
		ftpd.setUser("www.danilocgsilva.me");
		ftpd.setPass("yat178");
		
		ftpd.download("/cgi-bin/ftp/hwftp.txt", "D:\\Users\\Danilo\\Temporários\\hwftp5.txt");
	}
}
