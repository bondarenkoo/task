package generation.service.impl;

import generation.data.excel.ContactPerson;
import generation.service.XlsxToDocxService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


/**
 * In class implemented interface XlsõToDocõService.
** @author Bohdan
*/
public class XlsxToDocxServiceImpl implements XlsxToDocxService {

	ContactPerson person;
	XSSFCell fromNameCell;
	XSSFCell signedNameCell;
	XSSFCell toNameCell;
	XSSFCell toPositionCell;
	XSSFCell organizationCell;
	XSSFCell addressCell;
	XSSFCell departmentCell;
	XSSFCell positionCell;
	XSSFCell startDateCell;
	XSSFCell signedDateCell;

	/* (non-Javadoc)
	 * @see generation.service.XlsxToDocxService#loadXlsxTemplate(java.lang.String)
	 */
	@Override
	public Workbook loadXlsxTemplate(String name)
			throws EncryptedDocumentException, InvalidFormatException,
			IOException {
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see generation.service.XlsxToDocxService#getCellXSSF(org.apache.poi.xssf.usermodel.XSSFRow)
	 */
	@Override
	public void getCellXSSF(XSSFRow row) {
	
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

	/* 
	 * (non-Javadoc)
	 * @see generation.service.XlsxToDocxService#getStringCellValue(generation.data.ContactPerson)
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

	/*
	 * (non-Javadoc)
	 * @see generation.service.XlsxToDocxService#toString(java.lang.String)
	 */
	@Override
	public void toString(String text) {
		
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
		
	}

	/*
	 * (non-Javadoc)
	 * @see generation.service.XlsxToDocxService#createDocx(org.apache.poi.xssf.usermodel.XSSFRow, org.apache.poi.xwpf.usermodel.XWPFDocument)
	 */
	@Override
	public void createDocx(XSSFRow row, XWPFDocument docx) throws IOException {
		int n = row.getRowNum();
		FileOutputStream outputStream = new FileOutputStream(new File("C:\\result" + n + ".docx"));
		docx.write(outputStream);
		outputStream.close();
	}
}
