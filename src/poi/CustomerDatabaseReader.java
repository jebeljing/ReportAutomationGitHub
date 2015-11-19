package poi;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Customer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CustomerDatabaseReader {

	public static final String CUSTOMERDATABASE = "ACTIVE CARDS WITH SPIRE SEGMENTATION 080215 TO 092615.xlsx";
	
	public static List<Customer> readCustomerDatabaseReader() throws Exception {
		List<Customer> result = new ArrayList<Customer>();

		InputStream ExcelFileToRead = new FileInputStream("C:\\Users\\lalala123\\Desktop\\Report Automation\\" + CUSTOMERDATABASE);
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFSheet sheet = wb.getSheetAt(0);

		
		for (int i = 1; i < 649808; i++) {
			
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			
			Customer customer = new Customer();
			for (int j = 0; j < 4; j++)
			{
				XSSFCell cell= (XSSFCell) row.getCell(j);
				switch(j) {
				case 0:
					String cardNum = cell.getRawValue();
					customer.setCard_num(cardNum);
					break;
				case 1:
					String household = cell.getRawValue();
					customer.setHousehold(household);
					break;
				case 2:
					String segment = cell.getStringCellValue();
					customer.setSegment(segment);
					break;
				case 3:
					String segmentGroup = cell.getStringCellValue();
					customer.setSegGroup(segmentGroup);
					break;
				default: 
					throw new RuntimeException("Customer Database excel column format not right.");
				}
			}
			result.add(customer);
		}
		
//		Iterator<Row> rows = sheet.rowIterator();
//		rows.next();
//		while (rows.hasNext())
//		{
//			XSSFRow row = (XSSFRow) rows.next();
//			Iterator<Cell> cells = row.cellIterator();
//			Customer customer = new Customer();
//			while (cells.hasNext())
//			{
//				XSSFCell cell= (XSSFCell) cells.next();
//				int columnIndex = cell.getColumnIndex();
//				switch(columnIndex) {
//				case 0:
//					String cardNum = cell.getRawValue();
//					customer.setCard_num(cardNum);
//					break;
//				case 1:
//					String household = cell.getRawValue();
//					customer.setHousehold(household);
//					break;
//				case 2:
//					String segment = cell.getStringCellValue();
//					customer.setSegment(segment);
//					break;
//				case 3:
//					String segmentGroup = cell.getStringCellValue();
//					customer.setSegGroup(segmentGroup);
//					break;
//				default: 
//					throw new RuntimeException("Customer Database excel column format not right.");
//				}
//			}
//			result.add(customer);
//		}
		return result;
	}
	
}
