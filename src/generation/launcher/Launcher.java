package generation.launcher;

import generation.logger.SomeClass;
import generation.template.GenXlsToDoc;
import generation.template.GenXlsxToDocx;

import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


/*** Class designed to run project
** @author Bohdan
*/

public class Launcher {
	
	
	/**
	 * @param args
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		SomeClass log = new SomeClass();
		log.laucnherLog();
		String format = new Scanner(System.in).nextLine();
			if (format.equals("xls")) {
				new GenXlsToDoc();
			} else if (format.equals("xlsx")) {
				new GenXlsxToDocx();
			} else {
				log.launcherError();
				throw new IllegalArgumentException("Error, please input 'xls' or 'xlsx'");
			}
	}

}
