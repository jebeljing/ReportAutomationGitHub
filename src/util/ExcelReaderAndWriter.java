package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.CustomerDatabaseModel;
import model.ProgramRedemModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderAndWriter {
	
	private static final int TOTALROWS = 649808;
	private static final String reportName = "C:\\Users\\lalala123\\Desktop\\aaaaaaa.csv";
	
	private String fileName;
	private BufferedReader br = null;
	
	public ExcelReaderAndWriter(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public CustomerDatabaseModel extractCustomerDatebase() throws IOException {
		CustomerDatabaseModel model = new CustomerDatabaseModel();
		readFile();
		String line = null;
		Map<String, Customer> result = new HashMap<String, Customer>();
//		System.out.println(br.readLine());
//		System.out.println(br.readLine());
		for (int i = 0; i < TOTALROWS; i++) {
			Customer customer = new Customer();
			line = br.readLine();
			String[] split = line.split(",");
			if (line.startsWith("CARD_NBR")) {
				continue;
			}
			customer.setCard_num(split[0]);
			customer.setHousehold(split[1]);
			customer.setSegment(split[2]);
			customer.setSegGroup(split[3]);
			result.put(customer.getCard_num(), customer);
		}
		br.close();
		model.setCustomers(result);
		return model;
	}
	
	public ProgramRedemModel extractProgramRedemption(String fileName, CustomerDatabaseModel customerDB, int mode) throws IOException {
		ProgramRedemModel model = new ProgramRedemModel();
		this.setFileName(fileName);
		readFile();
		String line = null;
		while((line = br.readLine()) != null && !line.isEmpty()) {
			if (line.startsWith("STORE") || line.startsWith("CARD_NBR")) continue;
			String[] split = line.split(",");
			String cardNum = split[mode];
			model.getCardNums().add(cardNum);
		}
		model.computeSegGroup(customerDB);
		br.close();
		return model;
	}
	
	private void readFile() {
		try {
			br = new BufferedReader(new FileReader(fileName)); //"C:\\Users\\lalala123\\Desktop\\The Original_NC.NC"
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFile(List<Customer> customers) throws IOException {
		File file = new File(reportName);
		try {
			if (! file.exists())
					file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("STRATEGIC RESPONSE DASHBOARD");
			bw.newLine();
			bw.write("TOTAL  SHOPPER ACTIVITY THROUGH WEEK ENDING 9/20/15     Period 7 Week 2");
			bw.newLine();
			bw.write("MARSH DIGITAL COUPON");
			bw.newLine();
			StringBuilder sb = new StringBuilder();
			sb.append("Row Labels").append(",").append("TOTAL HH").append(",").append("TOTAL TARGET").append(",").append("W/E 8/30").append(",")
			.append("W/E 9/06").append(",").append("W/E 9/13").append(",").append("W/E 9/20");
			bw.write(sb.toString());
			bw.newLine();
			bw.write("Grand Total");
			bw.append(",");
			bw.write(String.valueOf(customers.size()));
//			String line = null;
//			for (int i = 0; i < 464569; i++) {
//				line = br.readLine();
//				String[] split = line.split(",");
//				StringBuilder sb = new StringBuilder();
//				}
//				
//				for (int k = 468; k < split.length; k++) {
//					sb.append(",").append(split[k]);
//				}
//				
//				bw.write(sb.toString());
//				bw.newLine();
//			}
			bw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
//	public void createReportFile(String fileName) throws Exception {
//		String summary = "SUMMARY";//name of sheet
//		String core = "CORE";
//		String secondary = "SECONDARY";
//		String occasional = "OCCASIONAL";
//		XSSFWorkbook wb = new XSSFWorkbook();
//		wb.createSheet(summary) ;
//		wb.createSheet(core);
//		wb.createSheet(secondary);
//		wb.createSheet(occasional);
//		FileOutputStream fileOut = new FileOutputStream(fileName);
//
//		//write this workbook to an Outputstream.
//		wb.write(fileOut);
//		fileOut.flush();
//		fileOut.close();
//	}
	
//	public void writeXLSFileTemplate(String fileName) throws Exception {
//		InputStream ExcelFileToRead = new FileInputStream(fileName);
//		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
//
//		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
//			XSSFSheet sheet = wb.getSheetAt(i);
//			XSSFRow row1 = sheet.createRow(0);
//			XSSFCell cell10 = row1.createCell(0);
//			cell10.setCellValue("                            STRATEGIC RESPONSE DASHBOARD");
//			
//			XSSFRow row2 = sheet.createRow(1);
//			XSSFCell cell20 = row2.createCell(0);
//			cell20.setCellValue("                      TOTAL  SHOPPER ACTIVITY THROUGH WEEK ENDING 9/20/15     Period 7 Week 2");
//			
//			XSSFRow row3 = sheet.createRow(2);
//			XSSFCell cell30 = row3.createCell(0);
//			cell30.setCellValue("MARSH DIGITAL COUPON");
//			
//			XSSFRow row4 = sheet.createRow(3);
//			XSSFCell cell40 = row4.createCell(0);
//			cell40.setCellValue("Row Labels");
//			XSSFCell cell41 = row4.createCell(1);
//			cell41.setCellValue("TOTAL HH");
//			XSSFCell cell42 = row4.createCell(2);
//			cell42.setCellValue("TOTAL TARGET");
//			XSSFCell cell43 = row4.createCell(3);
//			cell43.setCellValue("W/E 8/30");
//			XSSFCell cell44 = row4.createCell(4);
//			cell44.setCellValue("W/E 9/6");
//			XSSFCell cell45 = row4.createCell(5);
//			cell45.setCellValue("W/E 9/13");
//			XSSFCell cell46 = row4.createCell(6);
//			cell46.setCellValue("W/E 9/20");
//			
//			XSSFRow row5 = sheet.createRow(4);
//			XSSFCell cell50 = row5.createCell(0);
//			cell50.setCellValue("Core");
//			
//			XSSFRow row6 = sheet.createRow(5);
//			XSSFCell cell60 = row6.createCell(0);
//			cell60.setCellValue("Secondary");
//			
//			XSSFRow row7 = sheet.createRow(6);
//			XSSFCell cell70 = row7.createCell(0);
//			cell70.setCellValue("Occasional");
//			
//			XSSFRow row8 = sheet.createRow(7);
//			XSSFCell cell80 = row8.createCell(0);
//			cell80.setCellValue("Grand Total");
//		}
//		
//		FileOutputStream fileOut = new FileOutputStream(fileName);
//
//		//write this workbook to an Outputstream.
//		wb.write(fileOut);
//		fileOut.flush();
//		fileOut.close();
//	}
	
	public void writeXLSXFile(String fileName, CustomerDatabaseModel model) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(fileName);
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);

		XSSFSheet sheet = wb.getSheetAt(0);

		XSSFRow row5 = sheet.getRow(4);
		XSSFCell cell51 = row5.getCell(1);
		cell51.setCellValue(model.getNumOfCore());
		
		XSSFRow row6 = sheet.getRow(5);
		XSSFCell cell61 = row6.getCell(1);
		cell61.setCellValue(model.getNumOfSecondary());
		
		XSSFRow row7 = sheet.getRow(6);
		XSSFCell cell71 = row7.getCell(1);
		cell71.setCellValue(model.getNumOfOccasional());
		
		XSSFRow row8 = sheet.getRow(7);
		XSSFCell cell81 = row8.getCell(1);
		cell81.setCellValue(model.getCustomers().size());
		
		FileOutputStream fileOut = new FileOutputStream(fileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
}
