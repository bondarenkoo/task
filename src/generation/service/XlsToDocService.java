package generation.service;

import generation.data.excel.ContactPerson;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;


/*** interface describes the functional from this Class XlsToDocServiceImpl.
** @author Bohdan
*/
public interface XlsToDocService {

	/**
	 * @param name
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	Workbook loadXlsTemplate(String name) throws EncryptedDocumentException,
			InvalidFormatException, IOException;

	/**
	 * @param row
	 */
	void getCellHSSF(HSSFRow row);
	
	/**
	 * @param person
	 * @return
	 */
	ContactPerson getStringCellValue(ContactPerson person);
	
	
	/**
	 * @param range
	 */
	void getRange(Range range);
	
	
	
	/**
	 * @param row
	 * @param doc
	 * @throws IOException
	 */
	void createDoc(HSSFRow row, HWPFDocument doc) throws IOException;

}
