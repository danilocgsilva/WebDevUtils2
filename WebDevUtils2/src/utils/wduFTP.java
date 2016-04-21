package utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTP;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class wduFTP extends FTPClient {

	private boolean isLogged;

	public boolean isLogged() {
		return this.isLogged;
	}

	public wduFTP(String host, String user, String pass) {
		try {
			this.connect(host, 21);
			isLogged = this.login(user, pass);
			this.enterLocalPassiveMode();
			this.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void download(String serverPath, String localPath) throws Exception {

		if (!this.isLogged) {
			throw new Exception("You are not logged!");
		}

		File downloaded = new File(localPath);
		OutputStream os = new BufferedOutputStream(new FileOutputStream(downloaded));
		boolean downloadedWithSuccess = this.retrieveFile(serverPath, os);

		if (downloadedWithSuccess) {
			System.out.println("Successful downloaded. From \"" + serverPath + "\" to \"" + localPath + "\"");
		} else {
			String exceptionMessage = "";
			exceptionMessage = "Error in file downloading. ";
			exceptionMessage += "Source: " + serverPath + " ";
			exceptionMessage += "Destiny: " + localPath;

			throw new Exception(exceptionMessage);
		}
		os.close();
	}

	public void downloadFiles(String remoteSourcePath, String localDestinyPath) throws Exception {

		FTPFile[] fileList = this.listFiles(remoteSourcePath);
		String fileSeparator = System.getProperty("file.separator");

		if (!(new File(localDestinyPath).exists())) {
			new File(localDestinyPath).mkdir();
		}

		for (FTPFile file : fileList) {

			if (file.isFile()) {
				String sourcePathFile = remoteSourcePath + file.getName();
				String destinyPath = localDestinyPath + file.getName();

				this.download(sourcePathFile, destinyPath);
			}

			/*
			if (file.isDirectory()) {
				boolean selfDir = file.getName() == "." && file.getName() == "..";
				if (selfDir) {
				this.download(remoteSourcePath + "/" + file.getName(),
						localDestinyPath + fileSeparator + file.getName());
				}
			}
			*/

		}
	}

}
