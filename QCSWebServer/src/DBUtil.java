
import java.sql.*;

public class DBUtil {
	public static final String url = "jdbc:mysql://127.0.0.1:3306/qust?characterEncoding=utf8";
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "Ame-1135@";
	public Connection connection;
	public PreparedStatement preparedStatement;

	// 连接数据库
	public DBUtil(String sql) {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			preparedStatement = connection.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动错误！");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("获取数据库连接异常！");
			e.printStackTrace();
		}

	}

	// 关闭数据库
	public static void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试工具连接数据库是否成功
	 *
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
