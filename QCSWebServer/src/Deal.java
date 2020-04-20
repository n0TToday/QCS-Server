import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Deal {
	// ��ȡ��ǰϵͳʱ��
	public static String getDate() {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String dateNow = df.format(date);
		System.out.println("ϵͳʱ��:" + dateNow);
		return dateNow;
	}
//	��¼����
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
				System.out.println("���׼�¼�ɹ�");
				flag = "true";
			} else {
				System.out.println("��¼�쳣");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	���ؽ���
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
			System.out.println("��ѯ���׳ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return list;
	}
}
