// Reference: http://mrbool.com/java-ftp-how-to-download-file-with-java/29831

package FTP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FTPDownload {
	private static final int BUFFER_SIZE = 4096;
	public void download() { //this is a function
		long startTime = System.currentTimeMillis();
		String ftpPath = "htdocs/html/teste.json"; // name of the file which has to be download
		String fileName = "teste.json";
		String host = "www.danilocgsilva.me"; //ftp server
		String user = "www.danilocgsilva.me"; //user name of the ftp server
		String pass = "yat178" ; // password of the ftp server
		String ftpUrl = "ftp://" + host + ":" + pass + "@" + host + ":/" + ftpPath;
		String savePath = "D:\\Users\\Danilo\\Temporários\\" + fileName;
		ftpUrl = String.format(ftpUrl, user, pass, host);
		System.out.println("Connecting to FTP server");
		
		try {
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
			System.out.println("File downloaded");
			System.out.println("Download time in sec. is:-" + (endTime-startTime)/1000);
			outputStream.close();
			inputStream.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
