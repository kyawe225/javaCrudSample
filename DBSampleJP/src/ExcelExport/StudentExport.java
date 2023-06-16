package ExcelExport;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Bean.StudentBean;
import Connector.MysqlConnectorCU;
import DAO.StudentDAO;
import DTO.StudentDTO;

public class StudentExport {
	public boolean StudentExport() throws Exception {
		Workbook workBook=new XSSFWorkbook();
		Sheet sheet=workBook.createSheet("Students");
		sheet.setColumnWidth(0,sheet.getDefaultColumnWidth()*300);
		sheet.setColumnWidth(1,sheet.getDefaultColumnWidth()*300);
		sheet.setColumnWidth(2,sheet.getDefaultColumnWidth()*300);
		
		CellStyle header=workBook.createCellStyle();
		header.setAlignment(HorizontalAlignment.CENTER);
		header.setLocked(true);
		Font f=workBook.createFont();
		f.setBold(true);
		f.setFontHeightInPoints((short) 16);
		header.setFont(f);
		header.setBorderLeft(BorderStyle.THICK);
		header.setBorderRight(BorderStyle.THICK);
		header.setBorderTop(BorderStyle.THICK);
		header.setBorderBottom(BorderStyle.THICK);
		
		CellStyle allborder=workBook.createCellStyle();
		allborder.setBorderLeft(BorderStyle.THIN);
		allborder.setBorderRight(BorderStyle.THIN);
		allborder.setBorderTop(BorderStyle.THIN);
		allborder.setBorderBottom(BorderStyle.THIN);
		Row row=sheet.createRow(0);
		Cell cell1_1=row.createCell(0);
		Cell cell1_2=row.createCell(1);
		Cell cell1_3=row.createCell(2);
		
		cell1_1.setCellValue("No");
		cell1_2.setCellValue("Name");
		cell1_3.setCellValue("Score");
		
		cell1_1.setCellStyle(header);
		cell1_2.setCellStyle(header);
		cell1_3.setCellStyle(header);
		StudentDTO n=null;
		try(StudentDAO dao=new StudentDAO(new MysqlConnectorCU())){
			n=dao.getAll();
		}
		catch(Exception e) {
			
		}
		System.out.println(n.size());
		int rowNo=1;
		for(int i=0;i<n.size();i++){
			StudentBean s=n.get(i);
			Row temp=sheet.createRow(rowNo++);
			Cell temp_cell1_1=temp.createCell(0);
			Cell temp_cell1_2=temp.createCell(1);
			Cell temp_cell1_3=temp.createCell(2);
			
			temp_cell1_1.setCellValue(s.getNo());
			temp_cell1_2.setCellValue(s.getName());
			temp_cell1_3.setCellValue(s.getScore());
			
			temp_cell1_1.setCellStyle(allborder);
			temp_cell1_2.setCellStyle(allborder);
			temp_cell1_3.setCellStyle(allborder);
		}
		FileOutputStream out=new FileOutputStream("sample.xlsx");
		workBook.write(out);
		out.close();
		workBook.close();
		return true;
	}
}
