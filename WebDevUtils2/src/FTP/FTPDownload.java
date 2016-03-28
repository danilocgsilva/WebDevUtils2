// Original method: http://mrbool.com/java-ftp-how-to-download-file-with-java/29831

package FTP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FTPDownload {
	
	private static final int BUFFER_SIZE = 4096;
	private String host; //ftp server
	private String pass; // password of the ftp server
	private String user; //user name of the ftp server
	
	public void setHost(String host) {
		this.host = host;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	private String returnFtpUrl(String ftpFilePath) {
		return "ftp://" + user + ":" + pass + "@" + host + ":/" + ftpFilePath;
	}
	private String fileNameFromPath(String savePath) {
		String[] fragments = savePath.split("\\\\?/");
		return fragments[fragments.length - 1];
	}
	
	public void download(String ftpPath, String savePath) { //this is a function
		
		long startTime = System.currentTimeMillis();
		String ftpUrl = returnFtpUrl(ftpPath);
		
		try {
			System.out.println("Connecting to FTP server");
			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			InputStream inputStream = conn.getInputStream();
			long filesize = conn.getContentLength();
			System.out.println("Size of the file to download in kb is:-" + filesize/1024 );
			FileOutputStream outputStream = new FileOutputStream(savePath);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("File downloaded: " + ftpPath + " downloaded as " + fileNameFromPath(savePath));
			System.out.println("Download time in sec. is:-" + (endTime-startTime)/1000);
			outputStream.close();
			inputStream.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
