import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

public class Log {
	// 获取当前系统时间
	public static String logDate() {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String dateNow = df.format(date);
		System.out.println("系统时间:" + dateNow);
		return dateNow;
	}
	
//	记录Log
	public static String creatLog(String loguserid, String logmethod, String logthing,String logdata,String logresult) {
		String logdate = logDate();
		String sql = "insert into `loginfo`(logdate,loguserid,logmethod,logthing,logdata,logresult) value(?,?,?,?,?,?)";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, logdate);
			dbUtil.preparedStatement.setString(2, loguserid);
			dbUtil.preparedStatement.setString(3, logmethod);
			dbUtil.preparedStatement.setString(4, logthing);
			dbUtil.preparedStatement.setString(5, logdata);
			dbUtil.preparedStatement.setString(6, logresult);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Log记录成功");
				flag = "true";
			} else {
				System.out.println("Log异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	加载日志
	public static List<LoadLog> loadlog() {
		List<LoadLog> list = new ArrayList<LoadLog>();
		String sql = "select * from `loginfo`";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet resultSet = null;
		try {
			resultSet = dbUtil.preparedStatement.executeQuery();
			while (resultSet.next()) {
				LoadLog loadlog = new LoadLog();
				loadlog.setLogdate(resultSet.getString("logdate"));
				loadlog.setLoguserid(resultSet.getString("loguserid"));
				loadlog.setLogmethod(resultSet.getString("logmethod"));
				loadlog.setLogthing(resultSet.getString("logthing"));
				loadlog.setLogdata(resultSet.getString("logdata"));
				loadlog.setLogresult(resultSet.getString("logresult"));
				list.add(loadlog);
			}
			System.out.println("查询日志成功");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return list;
	}
}
