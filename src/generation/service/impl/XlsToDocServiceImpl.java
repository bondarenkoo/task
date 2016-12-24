package generation.service.impl;

import generation.data.excel.ContactPerson;
import generation.service.XlsToDocService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;



/***In Class implemented interface XlsToDocService.
** @author Bohdan
*/
public class XlsToDocServiceImpl implements XlsToDocService {
	
	ContactPerson person;
	HSSFCell fromNameCell;
	HSSFCell signedNameCell;
	HSSFCell toNameCell;
	HSSFCell toPositionCell;
	HSSFCell organizationCell;
	HSSFCell addressCell;
	HSSFCell departmentCell;
	HSSFCell positionCell;
	HSSFCell startDateCell;
	HSSFCell signedDateCell;

	/* (non-Javadoc)
	 * @see generation.service.XlsToDocService#loadXlsTemplate(java.lang.String)
	 */
	@Override
	public Workbook loadXlsTemplate(String name)
			throws EncryptedDocumentException, InvalidFormatException,
			IOException {
		return null;
	}

	/* (non-Javadoc)
	 * @see generation.service.XlsToDocService#getCellHSSF(org.apache.poi.hssf.usermodel.HSSFRow)
	 */
	@Override
	public void getCellHSSF(HSSFRow row) {
		
		this.fromNameCell = row.getCell(0);
		this.signedNameCell = row.getCell(1);
		this.toNameCell = row.getCell(2);
		this.toPositionCell = row.getCell(3);
		this.organizationCell = row.getCell(4);
		this.addressCell = row.getCell(5);
		this.departmentCell = row.getCell(6);
		this.positionCell = row.getCell(7);
		this.startDateCell = row.getCell(8);
		this.signedDateCell = row.getCell(9);

	}

	/* (non-Javadoc)
	 * @see generation.service.XlsToDocService#getStringCellValue(generation.data.ContactPerson)
	 */
	@Override
	public ContactPerson getStringCellValue(ContactPerson person) {

		person.setFromName(fromNameCell.getStringCellValue()); 
		person.setSignedName(signedNameCell.getStringCellValue());
		person.setToName(toNameCell.getStringCellValue());
		person.setToPosition(toPositionCell.getStringCellValue());
		person.setOrganization(organizationCell.getStringCellValue());
		person.setAddress(addressCell.getStringCellValue());
		person.setDepartment(departmentCell.getStringCellValue());
		person.setPosition(positionCell.getStringCellValue());
		person.setStartDate(startDateCell.getStringCellValue());
		person.setSignedDate(signedDateCell.getStringCellValue());
		this.person = person;
		return this.person;
	}
	
	/* (non-Javadoc)
	 * @see generation.service.XlsToDocService#getRange(org.apache.poi.hwpf.usermodel.Range)
	 */
	@Override
	public void getRange(Range range) {
		range.replaceText("{SignedName}", person.getSignedName());
		range.replaceText("{SignedDate}", person.getSignedDate());
		range.replaceText("{StartDate}", person.getStartDate());
		range.replaceText("{Department}", person.getDepartment());
		range.replaceText("{Position}", person.getPosition());
		range.replaceText("{ToPosition}", person.getToPosition());
		range.replaceText("{Organization}", person.getOrganization());
		range.replaceText("{ToName}", person.getToName());
		range.replaceText("{FromName}", person.getFromName());
		range.replaceText("{Address}", person.getAddress());

	}
	
	
	
	/* (non-Javadoc)
	 * @see generation.service.XlsToDocService#createDoc(org.apache.poi.hssf.usermodel.HSSFRow, org.apache.poi.hwpf.HWPFDocument)
	 */
	@Override
	public void createDoc(HSSFRow row, HWPFDocument doc) throws IOException {
		int n = row.getRowNum();
		FileOutputStream outputStream = new FileOutputStream(new File("C:\\result" + n + ".doc"));
		doc.write(outputStream);
		outputStream.close();	
	}

}
