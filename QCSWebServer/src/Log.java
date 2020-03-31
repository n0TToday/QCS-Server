import java.sql.Timestamp;
import java.text.*;

public class Log {
	// ��ȡ��ǰϵͳʱ��
	public static String logDate() {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String dateNow = df.format(date);
		System.out.println("ϵͳʱ��:" + dateNow);
		return dateNow;
	}
	
//	��¼Log
	public static String creatLog(String loguserid, String logmethod, String logthing,String logdata,String logresult) {
		String logdate = logDate();
		String sql = "insert into `loginfo`(logdate,loguserid,logmethod,logthing,,logdata,logresult) value(?,?,?,?,?,?)";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, logdate);
			dbUtil.preparedStatement.setString(2, loguserid);
			dbUtil.preparedStatement.setString(3, logmethod);
			dbUtil.preparedStatement.setString(4, logthing);
			dbUtil.preparedStatement.setString(5, logdata);
			dbUtil.preparedStatement.setString(5, logresult);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("Log��¼�ɹ�");
				flag = "true";
			} else {
				System.out.println("Log�쳣");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
	
}
