package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Connector.IConnection;
import DTO.StudentCreateDTO;
import DTO.StudentDTO;
import Bean.StudentBean;

public class StudentDAO implements AutoCloseable {
	private IConnection c;

	public StudentDAO(IConnection con) {
		c = con;
	}

	public StudentDTO getAll() {
		try {
			c.open();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select * from student;");
			StudentDTO dto = new StudentDTO();
			while (rs.next()) {
				StudentBean temp = new StudentBean(rs.getInt("no"), rs.getString("name"), rs.getInt("score"));
				dto.add(temp);
//					 rs.getInt("no");
//					 rs.getString("name");
//					 rs.getInt("score");
			}
			rs.close();
			s.close();
			c.close();
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void close() throws Exception {
		c=null;
	}
}
