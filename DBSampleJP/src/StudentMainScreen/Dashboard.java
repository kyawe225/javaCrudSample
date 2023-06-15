package StudentMainScreen;

import Connector.*;
import StudentMainScreen.Controllers.StudentController;

import java.sql.*;
import java.util.Scanner;

public class Dashboard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IConnection c = ConnectionFactory.getConnection(ConnectionType.Mysql);
		Scanner scanner = new Scanner(System.in);
		try {
			int menu = -1;
			while (menu != 0) {
				if (menu < 0) {
					System.out.println("Write 0 to quit:");
					System.out.println("1 : student");
					System.out.println("0 : to Quit");
					
					menu = Integer.parseInt(scanner.nextLine());
				}
				switch (menu) {
				case 1:
					System.out.println("1 : List All");
					System.out.println("2 : Add New Student");
					System.out.println("3 : Update Student");
					System.out.println("4 : Delete Student");
					System.out.println("0 : Back");
					int list = Integer.parseInt(scanner.nextLine());
					StudentController student = new StudentController(c,scanner);
					switch (list) {
					case 1:
						student.getAll();
						break;
					case 2:
						student.insert();
						break;
					case 3:
						student.update();
						break;
					case 4:
						student.delete();
						break;
					case 0:
						menu = -1;
						break;
					}
					break;
				default:
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner.close();
	}

}
