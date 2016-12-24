package generation.service;

import generation.data.excel.ContactPerson;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;



/**
 * interface describes the functional from this Class XlsxToDocxServiceImpl.
 * @author Bohdan
 */
public interface XlsxToDocxService {
	
	/**
	 * @param name
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	Workbook loadXlsxTemplate(String name) throws EncryptedDocumentException,
	InvalidFormatException, IOException;
	
	/**
	 * @param row
	 */
	void getCellXSSF(XSSFRow row);
	
	/**
	 * @param person
	 * @return
	 */
	ContactPerson getStringCellValue(ContactPerson person);
	
	/**
	 * @param text
	 */
	void toString (String text);
	
	/**
	 * @param row
	 * @param docx
	 * @throws IOException
	 */
	void createDocx(XSSFRow row, XWPFDocument docx) throws IOException;

}
