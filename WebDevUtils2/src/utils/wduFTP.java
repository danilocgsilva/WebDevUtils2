package utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class wduFTP extends FTPClient {
	
	public wduFTP(String host, String user, String pass) {
		try {
			this.connect(host, 21);
			this.login(user, pass);
			this.enterLocalPassiveMode();
			this.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void download(String serverPath, String localPath) {
		try {
			File downloaded = new File(localPath);
			OutputStream os = new BufferedOutputStream(new FileOutputStream(downloaded));
			this.retrieveFile(serverPath, os);
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
