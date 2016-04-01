import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
		
		
		/* Using a non Apache Net FTP Clas */
		
		/*FTPDownload ftpd = new FTPDownload();
		ftpd.setHost("www.danilocgsilva.me");
		ftpd.setUser("www.danilocgsilva.me");
		ftpd.setPass("yat178");
		
		ftpd.download("/cgi-bin/ftp/hwftp.txt", "D:\\Users\\Danilo\\Temporários\\hwftp5.txt");*/
		
		
		
		/* Using the Apache Net FTP Cliente */
        FTPClient ftpClient = new FTPClient();
        try {
 
            ftpClient.connect("www.danilocgsilva.me", 21);
            ftpClient.login("www.danilocgsilva.me", "yat178");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "/cgi-bin/ftp/hwftp.txt";
            File downloadFile1 = new File("C:\\Users\\Danilo\\temporarios\\hwftp1.txt");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();
 
            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }
 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		
	}
}
