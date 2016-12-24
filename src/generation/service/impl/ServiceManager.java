package generation.service.impl;

import generation.service.XlsToDocService;
import generation.service.XlsxToDocxService;


/**
 * Class for manager service
 * @author Bogdan
 *
 */
public class ServiceManager {

	private XlsToDocService xlsToDocService;
	private XlsxToDocxService xlsxToDocxService;
	
	/**
	 * @return
	 */
	public XlsToDocService getXlsToDocService(){
		return xlsToDocService;
	}
	/**
	 * @return
	 */
	public XlsxToDocxService getXlsxToDocxService(){
		return xlsxToDocxService;
	}
	/**
	 * Created service
	 */
	private ServiceManager(){
		super();
		xlsToDocService = (XlsToDocService) ServiceFactory.createService(new XlsToDocServiceImpl());
		xlsxToDocxService = (XlsxToDocxService) ServiceFactory.createService(new XlsxToDocxServiceImpl());
	}
	/**
	 * @return
	 */
	public static ServiceManager getInstance(){
		return new ServiceManager();
	}
}
