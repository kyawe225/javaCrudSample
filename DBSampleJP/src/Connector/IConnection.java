package Connector;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public interface IConnection {
	public void open() throws ClassNotFoundException,IOException;
	public void close() throws SQLException;
	public PreparedStatement createPreparedStatement(String sql);
	public Statement createStatement();
	public void beginTranscation();
	public void commit();
	public void rollback();
	public Savepoint createSavepoint(String name);
}
