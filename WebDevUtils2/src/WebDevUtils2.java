

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import content.ReadContentHandledException;
import hosts.Hosts;
import utils.UtilsMethods;
import utils.wduFTP;


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
		wduFTP ftp = new wduFTP("www.danilocgsilva.me", "www.danilocgsilva.me", "yat178");
		ftp.download("/htdocs/arquivosdeinformacoesdophp.php", "C://Users//Danilo//temporarios//tamanhos padroes de tela" + System.currentTimeMillis()  + ".txt");
	}
}
