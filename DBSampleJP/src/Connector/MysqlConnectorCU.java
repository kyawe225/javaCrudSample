package Connector;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Properties;


public class MysqlConnectorCU implements IConnection {
	private Connection con;

	@Override
	public void open() throws ClassNotFoundException, IOException {
		InputStream io = MysqlConnectorCU.class.getClassLoader().getResourceAsStream("mysql.properties");
		Properties p = new Properties();
		p.load(io);
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			con = DriverManager.getConnection(p.getProperty("db.url"), p.getProperty("db.user"),
					p.getProperty("db.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws SQLException {
		if (!con.isClosed()) {
			con.close();
		}
	}
	
	@Override
	public PreparedStatement createPreparedStatement(String sql) {
		return (PreparedStatement)this.createStatement(StatementType.PREPARED,sql);
	}

	private Statement createStatement(StatementType statementType, String sql) {
		try {
			if (statementType == StatementType.PREPARED) {
				PreparedStatement p = con.prepareStatement(sql);
				return p;
			} else {
				Statement p = con.createStatement();
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Statement createStatement() {
		// TODO Auto-generated method stub
		return this.createStatement(StatementType.NORMAL,"");
	}

	@Override
	public void beginTranscation() {
		// TODO Auto-generated method stub
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Savepoint createSavepoint(String name) {
		// TODO Auto-generated method stub
		try {
			Savepoint s=con.setSavepoint(name);
			return s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
