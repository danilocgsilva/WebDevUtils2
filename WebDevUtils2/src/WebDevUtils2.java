

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import content.ReadContentHandledException;
import hosts.Hosts;
import utils.UtilsMethods;
import utils.wduFTP;
import utils.wduLog;


public class WebDevUtils2 {

	public static void main(String[] args) {
		UtilsMethods um = new UtilsMethods();
		um.singleLineConsoleMessage("Olá pessoas!!!");
		um.separator();
		Hosts hs = new Hosts();
		um.singleLineConsoleMessage(hs.getContent());
		um.separator();
		um.singleLineConsoleMessage(hs.getHostPath());
		um.separator();
		wduLog log = new wduLog("C://Users//Danilo//temporarios//log.txt");
		wduFTP ftp = new wduFTP("www.danilocgsilva.me", "www.danilocgsilva.me", "yat178", log);
		
		try {
			ftp.download("/htdocs/arquivosdeinformacoesdophp.php", "C://Users//Danilo//temporarios//tamanhos padroes de tela" + System.currentTimeMillis()  + ".txt");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			ftp.startDownloadRecursive("/htdocs/", "C://Users//Danilo//temporarios//" + "teste_" + System.currentTimeMillis() + "//");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
