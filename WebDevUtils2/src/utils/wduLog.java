package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class wduLog {
	
	private String logPath;
	
	private SimpleDateFormat simpleDateFormat;

	public wduLog(String logPath) {
		this.logPath = logPath;
		this.simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.SSS");
	}

	public void writeLog(String logMessage) {
		Date timeNow = new Date();
		String writeTime = this.simpleDateFormat.format(timeNow);
		String generalLogMessage = writeTime + " " + logMessage;
		BufferedWriter bufferedWriter = null;
		
		try {
			FileWriter fileWriter = new FileWriter(this.logPath, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(generalLogMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			timeNow = null;
		}
	}
	
	public void writeConsole(String logMessage) {
		Date nowTime = new Date();
		String writeDate = this.simpleDateFormat.format(nowTime);
		System.out.println(writeDate + " " + logMessage);
		nowTime = null;
	}
}
