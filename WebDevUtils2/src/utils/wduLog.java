package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;

public class wduLog {
	private String logPath;
	private Date writeTime;

	public wduLog(String logPath) {
		this.logPath = logPath;
		this.writeTime = new Date();
	}

	public void writeLog(String logMessage) {
		File logFile = new File(this.logPath);
		try {
			FileWriter fw = new FileWriter(logFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(logMessage);
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
