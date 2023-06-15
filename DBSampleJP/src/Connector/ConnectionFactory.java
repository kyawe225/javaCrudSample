package Connector;

public class ConnectionFactory {
	public static IConnection getConnection(ConnectionType con) {
		switch(con) {
		case Mysql:
			return new MysqlConnectorCU();
		default :
			return null;
		}
	}
}
