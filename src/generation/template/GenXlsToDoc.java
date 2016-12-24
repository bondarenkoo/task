package generation.template;

import generation.data.excel.ContactPerson;
import generation.logger.SomeClass;
import generation.service.XlsToDocService;
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
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;

/**
 * Class generated data from xls to doc
 * @author Bohdan
 */
public class GenXlsToDoc {
	

	private ServiceManager serviceManager;

	/**
	 * @return
	 */
	public XlsToDocService getXlsToDocService() {
		return serviceManager.getXlsToDocService();
	}

	/**
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public GenXlsToDoc() throws EncryptedDocumentException, InvalidFormatException, IOException {
		generation();
	}
	
	/**
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 */
	@SuppressWarnings("resource")
	public  void generation() throws IOException, EncryptedDocumentException, InvalidFormatException {
		
		serviceManager = ServiceManager.getInstance();
		
		List<ContactPerson> contacts = new ArrayList<ContactPerson>();
		SomeClass log = new SomeClass();
		log.pathToXls();
		
		String pathToExcel = new Scanner(System.in).nextLine();
		
		InputStream xls = new FileInputStream(pathToExcel); 
		
		HSSFWorkbook workBook = new HSSFWorkbook(xls);
		
		HSSFSheet sheet = workBook.getSheetAt(0);
		
		Iterator<Row> rows = sheet.rowIterator(); 
		
		if (rows.hasNext()) {
			rows.next();
		}

		log.pathToDoc();
		String pathToWorld = new Scanner(System.in).nextLine();
		
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow) rows.next();

			getXlsToDocService().getCellHSSF(row); 

			ContactPerson person = new ContactPerson();
			
			contacts.add(getXlsToDocService().getStringCellValue(person)); 
		
			FileInputStream inputStream = new FileInputStream(new File(pathToWorld));
			
			HWPFDocument doc = new HWPFDocument(inputStream);
			
			checkContains(doc,log);
			
			Range range = doc.getRange();
			
			getXlsToDocService().getRange(range); 
			

			getXlsToDocService().createDoc(row, doc); 
			log.createDoc();
			
			inputStream.close();

		}
	}
	/**
	 * @param doc
	 * @param log
	 */
	public  void checkContains(HWPFDocument doc, SomeClass log){
		String text="";
		@SuppressWarnings("resource")
		WordExtractor extractor = new WordExtractor(doc);
        String[] fileData = extractor.getParagraphText();
        for (int i = 0; i < fileData.length; i++) {
            if (fileData[i] != null) {
            	text += fileData[i];
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
}

