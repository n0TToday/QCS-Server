
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	// URL指向要访问的数据库名
	private static String dburl = "jdbc:mysql://127.0.0.1:3306/qust";
	// 连接数据库的用户名
	private static String dbUserName = "root";
	// MySQL数据库用户的密码
	private static String dbPassword = "Ame-1135@";
	// 驱动程序名
	private static String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * 创建数据库连接
	 *
	 * @return
	 */
	public static Connection getCon() {
		Connection con = null;
		try {
			// 加载JDBC驱动。
			Class.forName(jdbcName);
			// 创建连接数据库的对象con
			con = DriverManager.getConnection(dburl, dbUserName, dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动错误！");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("获取数据库连接异常！");
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param con 数据库连接Connection对象
	 */
	public static void closeCon(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("数据库连接关闭异常！");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭所有资源
	 * 
	 * @param con   数据库连接对象
	 * @param pstmt Statement对象
	 * @param rs    结果集
	 */
	public static void closeAll(Connection con, Statement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("关闭结果集异常！");
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("关闭Statement对象异常！");
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("数据库连接关闭异常！");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试DBUtil工具连接数据库是否成功
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = DBUtil.getCon();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接成功！");
		} finally {
			try {
				DBUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
