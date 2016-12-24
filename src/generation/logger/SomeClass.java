package generation.logger;

import generation.launcher.Launcher;
import generation.template.GenXlsToDoc;
import generation.template.GenXlsxToDocx;
import java.util.logging.Logger;


/**
 *Class displays messages
 * @author Bogdan
 */
public class SomeClass {
	private static Logger log1 = Logger.getLogger(Launcher.class.getName());
	private static Logger log2 = Logger.getLogger(GenXlsToDoc.class.getName());
	private static Logger log3 = Logger.getLogger(GenXlsxToDocx.class.getName());
		

	public void laucnherLog() {
		System.out.println("Please, input format 'xls' or 'xlsx'");
		log1.info("��� �������������� ���������!");
	}
	public void launcherError(){
		log1.severe("��� ��������� ������");
	}

	public void pathToXls() {
		System.out.println("Input path to excel file");
		log2.info("��� �������������� ���������!");
	}
	
	public void pathToDoc(){
		System.out.println("Input path to world file");
		log2.info("��� �������������� ���������!");
	}
	
	public void CheckContains(){
		log2.severe("��� ��������� ������");
	}
	
	public void createDoc(){
		System.out.println("Generation is successful. file created on disk C");
		log2.info("��� �������������� ���������!");
	}
	
	public void pathToXlsx() {
		System.out.println("Input path to excel file");
		log3.info("��� �������������� ���������!");
	}
	
	public void pathToDocx(){
		System.out.println("Input path to world file");
		log3.info("��� �������������� ���������!");
	}
	
	public void createDocx(){
		System.out.println("Generation is successful. file created on disk C");
		log3.info("��� �������������� ���������!");
	}
	
	
	

}
