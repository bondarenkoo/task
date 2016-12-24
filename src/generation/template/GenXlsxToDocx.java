package generation.template;

import generation.data.excel.ContactPerson;
import generation.logger.SomeClass;
import generation.service.XlsxToDocxService;
import generation.service.impl.ServiceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/***
 *Class generated data from xlsx to docx
 ** 
 * @author Bohdan
 */

public class GenXlsxToDocx {

	private ServiceManager serviceManager;

	/**
	 * @return
	 */
	public XlsxToDocxService getXlsxToDocxService() {
		return serviceManager.getXlsxToDocxService();
	}

	/**
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public GenXlsxToDocx() throws EncryptedDocumentException,
			InvalidFormatException, IOException {
		generation();
	}

	/**
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void generation() throws EncryptedDocumentException,
			InvalidFormatException, IOException {

		serviceManager = ServiceManager.getInstance();

		List<ContactPerson> contacts = new ArrayList<ContactPerson>();

		SomeClass log = new SomeClass();
		log.pathToXlsx();
		String pathToExcel = new Scanner(System.in).nextLine();

		InputStream xlsx = new FileInputStream(pathToExcel);

		XSSFWorkbook workbook = new XSSFWorkbook(xlsx);

		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Iterator<Row> rows = sheet.rowIterator(); 

		if (rows.hasNext()) {
			rows.next();
		}

		log.pathToDocx();
		String pathToWorld = new Scanner(System.in).nextLine();

		while (rows.hasNext()) {
			
			XSSFRow row = (XSSFRow) rows.next();

			getXlsxToDocxService().getCellXSSF(row); 

			ContactPerson person = new ContactPerson();

			contacts.add(getXlsxToDocxService().getStringCellValue(person));

			FileInputStream inputStream = new FileInputStream(new File(
					pathToWorld));

			XWPFDocument docx = new XWPFDocument(inputStream);
			checkContains(docx, log);
	
			for (XWPFParagraph p : docx.getParagraphs()) {
				for (XWPFRun r : p.getRuns()) {
					
					String text= r.toString();
					r.setText(replaceText(text, person), 0);
					text = r.toString();
					

				}

			}
			log.createDocx();
			getXlsxToDocxService().createDocx(row, docx);

			inputStream.close();
		}

	}
	
	/**
	 * @param docx
	 * @param log
	 */
	private  void checkContains(XWPFDocument docx, SomeClass log) {
		String text="";
		for (XWPFParagraph p : docx.getParagraphs()) {
			for (XWPFRun r : p.getRuns()) {				
				text+= r.toString();
			}

		}
			if (!(text.contains("{ToPosition}") && text.contains("{Organization}")
					&& text.contains("{ToName}") && text.contains("{FromName}")
					&& text.contains("{Address}") && text.contains("{StartDate}")
					&& text.contains("{Department}") && text.contains("{Position}")
					&& text.contains("{SignedName}")
					&& text.contains("{SignedDate}"))){	
				log.CheckContains();
				throw new IllegalArgumentException("Error, document does not required content");
			}
		
	}

	/**
	 * @param text
	 * @param person
	 * @return
	 */
	private  String replaceText(String text, ContactPerson person) {
		text = text.replace("{ToPosition}", person.getToPosition());
		text = text.replace("{Organization}", person.getOrganization());
		text = text.replace("{ToName}", person.getToName());
		text = text.replace("{FromName}", person.getFromName());
		text = text.replace("{Address}", person.getAddress());
		text = text.replace("{StartDate}", person.getStartDate());
		text = text.replace("{Department}", person.getDepartment());
		text = text.replace("{Position}", person.getPosition());
		text = text.replace("{SignedName}", person.getSignedName());
		text = text.replace("{SignedDate}", person.getSignedDate());
		return text;
	}

}
