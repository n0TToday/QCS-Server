import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Deal {
	// 获取当前系统时间
	public static String getDate() {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String dateNow = df.format(date);
		System.out.println("系统时间:" + dateNow);
		return dateNow;
	}
//	记录交易
	public static String createDeal(String dealsource, String dealdata,String dealperson) {
		String dealdate = getDate();
		String sql = "insert into `dealinfo`(dealsource,dealdate,dealdata,dealperson) value(?,?,?,?)";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, dealsource);
			dbUtil.preparedStatement.setString(2, dealdate);
			dbUtil.preparedStatement.setString(3, dealdata);
			dbUtil.preparedStatement.setString(4, dealperson);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("交易记录成功");
				flag = "true";
			} else {
				System.out.println("记录异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	加载交易
	public static List<LoadDeal> loaddeal(String searchtype,String searchdata) {
		List<LoadDeal> list = new ArrayList<LoadDeal>();
		String sql = "select * from `dealinfo` where ?=?";
		DBUtil dbUtil = new DBUtil(sql);
		ResultSet resultSet = null;
		try {
			dbUtil.preparedStatement.setString(1, searchtype);
			dbUtil.preparedStatement.setString(2, searchdata);
			resultSet = dbUtil.preparedStatement.executeQuery();
			while (resultSet.next()) {
				LoadDeal loaddeal = new LoadDeal();
				loaddeal.setDealsource(resultSet.getString("dealsource"));
				loaddeal.setDealdate(resultSet.getString("dealdate"));
				loaddeal.setDealdata(resultSet.getString("dealdata"));
				loaddeal.setDealperson(resultSet.getString("dealperson"));
				list.add(loaddeal);
			}
			System.out.println("查询交易成功");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return list;
	}
}
