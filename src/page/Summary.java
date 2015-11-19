package page;

import model.CustomerDatabaseModel;
import model.ProgramRedemModel;
import util.ExcelReaderAndWriter;

public class Summary {

	public static void fillInSummaryPage() throws Exception {
		//	List<Customer> customerDB = CustomerDatabaseReader.readCustomerDatabaseReader();
		//	System.out.println(customerDB.size());
		//	System.out.println(customerDB.get(3).toString());
		ExcelReaderAndWriter er = new ExcelReaderAndWriter("C:\\Users\\lalala123\\Desktop\\Report Automation\\csv\\ACTIVE CARDS WITH SPIRE SEGMENTATION 080215 TO 092615.csv");
		CustomerDatabaseModel model = er.extractCustomerDatebase();
		System.out.println(model.getCustomers().size());
		
		ProgramRedemModel programTargetModel = er.extractProgramRedemption("C:\\Users\\lalala123\\Desktop\\Report Automation\\csv\\CATALINA BASKET PRODUCT PROGRAM TARGET CARDS.csv", model, 0);
		System.out.println(programTargetModel.getNumOfCore() + programTargetModel.getNumOfOccasional() + programTargetModel.getNumOfSecondary());
		System.out.println(programTargetModel.getNumOfCore());
		System.out.println(programTargetModel.getNumOfSecondary());
		System.out.println(programTargetModel.getNumOfOccasional());
		
		ProgramRedemModel programRedemModel = er.extractProgramRedemption("C:\\Users\\lalala123\\Desktop\\Report Automation\\csv\\BPP REDEMPTION BASKETS 083015 TO 090515.csv", model, 1);
		System.out.println(programRedemModel.getNumOfCore() + programRedemModel.getNumOfOccasional() + programRedemModel.getNumOfSecondary());
		System.out.println(programRedemModel.getNumOfCore());
		System.out.println(programRedemModel.getNumOfSecondary());
		System.out.println(programRedemModel.getNumOfOccasional());
		
		//	er.writeFile(beginWork);
//		er.writeXLSXFile(model);
//		er.createReportFile("C:\\Users\\lalala123\\Desktop\\Report Automation\\bbbbbbb.xlsx");
//		er.writeXLSFileTemplate("C:\\Users\\lalala123\\Desktop\\Report Automation\\bbbbbbb.xlsx");
		er.writeXLSXFile("C:\\Users\\lalala123\\Desktop\\Report Automation\\mytest.xlsx", model);
	}
	
	public static void createCorePage() {
		
	}
}
