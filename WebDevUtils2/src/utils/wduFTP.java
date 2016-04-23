package utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTP;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class wduFTP extends FTPClient {

	private boolean isLogged;

	private wduLog ftpLog;

	public boolean isLogged() {
		return this.isLogged;
	}

	public wduFTP(String host, String user, String pass, wduLog log) {
		this.ftpLog = log;
		try {
			this.connect(host, 21);
			isLogged = this.login(user, pass);
			this.enterLocalPassiveMode();
			this.setFileType(FTP.BINARY_FILE_TYPE);
			this.setControlKeepAliveTimeout(300);
			this.setConnectTimeout(300);
			log.writeLog("Successfull conected and logged.");
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
			String message = "Successful downloaded. From \"" + serverPath + "\" to \"" + localPath + "\"";
			ftpLog.writeLog(message);
			ftpLog.writeConsole(message);
		} else {
			String exceptionMessage = "";
			exceptionMessage = "Error in file downloading. ";
			exceptionMessage += "Source: " + serverPath + " ";
			exceptionMessage += "Destiny: " + localPath;

			ftpLog.writeLog(exceptionMessage);
			ftpLog.writeConsole(exceptionMessage);
		}
		os.close();
	}

	public void startDownloadRecursive(String remoteSourcePath, String localDestinyPath) {
		String job = createJobId();
		this.ftpLog.writeLog("------");
		this.ftpLog.writeLog("Job " + job + " created.");

		try {
			downloadRecursive(remoteSourcePath, localDestinyPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		this.ftpLog.writeLog("Job " + job + " finished.");
	}

	public void downloadRecursive(String remoteSourcePath, String localDestinyPath) throws Exception {

		FTPFile[] fileList = this.listFiles(remoteSourcePath);

		if (!(new File(localDestinyPath).exists())) {
			new File(localDestinyPath).mkdir();
		}

		for (FTPFile file : fileList) {

			if (file.isFile()) {
				String sourcePathFile = remoteSourcePath + file.getName();
				String destinyPath = localDestinyPath + file.getName();

				try {
					this.download(sourcePathFile, destinyPath);
				} catch (Exception ex) {
					this.ftpLog.writeLog("ERROR: Problem in download. Source: \"" + sourcePathFile + "\". Destiny: \"" + destinyPath + "\". Exception message: " + ex.getMessage());
				}
				
			} else if (file.isDirectory()) {
				if (!file.getName().equals(".") && !file.getName().equals("..")) {

					String sourcePathFolder = remoteSourcePath + file.getName() + "/";
					String destinyFolder = localDestinyPath + file.getName() + "//";

					this.downloadRecursive(sourcePathFolder, destinyFolder);
				}
			}
		}

	}

	private String createJobId() {
		String letterUsedToCreateId = "abcdefghijklmnopqrstuvwyxz1234567890";
		String id = "";
		Random r = new Random();
		for (int i = 0; i < 32; i++) {
			id += letterUsedToCreateId.charAt(r.nextInt(letterUsedToCreateId.length() - 1));
		}
		return id;
	}

}
