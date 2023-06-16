package StudentMainScreen.Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Connector.IConnection;
import ExcelExport.StudentExport;

public class StudentController {
	private IConnection c;
	private Scanner s;

	public StudentController(IConnection con,Scanner ss) {
		c = con;
		s=ss;
	}

	public void getAll() {
		try {
			c.open();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from student;");
			System.out.println("|------------------------|");
			System.out.println("|no \t|name  \t|score   |");
			System.out.println("|------------------------|");
			while (rs.next()) {
				System.out.print("|" + rs.getInt("no") + "\t");
				System.out.print("|" + rs.getString("name") + "\t");
				System.out.print("|" + rs.getInt("score") + "\t |");
				System.out.println();
				System.out.println("|------------------------|");
			}
			rs.close();
			s.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insert() {
		try {
			System.out.print("Enter Student Name : ");
			String name=s.nextLine();
			System.out.print("Enter Student Score : ");
			int score=Integer.parseInt(s.nextLine());
			c.open();
			PreparedStatement ps = c.createPreparedStatement("insert into student(name,score) values(?,?);");
			ps.setString(1, name);
			ps.setInt(2, score);
			ps.executeUpdate();
			ps.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void update() {
		try {
			c.open();
			System.out.print("Enter Student Number : ");
			int no=Integer.parseInt(s.nextLine());
			PreparedStatement pst = c.createPreparedStatement("select count(*) from student where no=?;");
			pst.setInt(1, no);
			ResultSet rs=pst.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			pst.close();
			if(count>0) {
				System.out.print("Enter Student Name : ");
				String name=s.nextLine();
				System.out.print("Enter Student Score : ");
				int score=Integer.parseInt(s.nextLine());
				PreparedStatement ps = c.createPreparedStatement("update student set name=?,score=? where no=?;");
				ps.setString(1, name);
				ps.setInt(2, score);
				ps.setInt(3, no);
				int rs1=ps.executeUpdate();
				if(rs1>0) {
					System.out.println("Updated Successfully");
				}else {
					System.out.println("Updated Not Successfully");				
				}
				ps.close();
				c.close();
			}
			else {
				System.out.println("No student found!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delete() {
		try {
			System.out.print("Enter Student Number : ");
			int no=Integer.parseInt(s.nextLine());
			c.open();
			PreparedStatement ps = c.createPreparedStatement("delete from student where no=?;");
			ps.setInt(1, no);
			int rs=ps.executeUpdate();
			if(rs>0) {
				System.out.println("Deleted Successfully");
			}else {
				System.out.println("Deleted Not Successfully");				
			}
			ps.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void export() {
		StudentExport ex=new StudentExport();
		try {
		boolean check=ex.StudentExport();
		if(check) {
			System.out.println("Export Successfully");
		}else
		System.out.println("Export နော့  Successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
