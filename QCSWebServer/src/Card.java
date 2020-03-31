import java.sql.ResultSet;

public class Card {
//	创建卡
	public static String creatCard(String cardid, String cardstats, String cardbalance, String cardusername, String carduserid) {
		String sql = "insert into `cardinfo`(cardid,cardstats,cardbalance,cardusername,carduserid) value(?,?,?,?,?)";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardid);
			dbUtil.preparedStatement.setString(2, cardstats);
			dbUtil.preparedStatement.setString(3, cardbalance);
			dbUtil.preparedStatement.setString(4, cardusername);
			dbUtil.preparedStatement.setString(5, carduserid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("创建卡信息成功");
				flag = "true";
			} else {
				System.out.println("创建异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	查询余额
	public static String getbalance(String cardid) {
		String flag = "false";
		boolean p = false;
		String sql = "select * from `cardinfo` where cardid=?";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.preparedStatement.setString(1, cardid);
			ResultSet i = dbUtil.preparedStatement.executeQuery();
			p = i.first();
			if (p) {
				flag = i.getString("cardbalance");
			}
			System.out.println("余额:" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	获取卡状态
	public static String getstats(String cardid) {
		String flag = "false";
		boolean p = false;
		String sql = "select * from `cardinfo` where cardid=?";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.preparedStatement.setString(1, cardid);
			ResultSet i = dbUtil.preparedStatement.executeQuery();
			p = i.first();
			if (p) {
				flag = i.getString("cardstats");
			}
			System.out.println("卡状态:" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	充值卡
	public static String rechargeCard(String cardid, String cardbalance) {
		String sql = "UPDATE cardinfo SET cardbalance = ? WHERE cardid = ?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardbalance);
			dbUtil.preparedStatement.setString(2, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.printf("充值成功,充值后金额为",cardbalance);
				flag = "true";
			} else {
				System.out.println("充值异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	修改卡状态
	public static String restatsCard(String cardid, String cardstats) {
		String sql = "UPDATE cardinfo SET cardstats = ? WHERE cardid = ?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardstats);
			dbUtil.preparedStatement.setString(2, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.printf("修改成功，卡状态为：",cardstats);
				flag = "true";
			} else {
				System.out.println("修改卡状态异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	注销卡
	public static String deleteCard(String cardid) {
		String sql = "delete * from `cardinfo` where cardid=?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.printf("注销成功");
				flag = "true";
			} else {
				System.out.println("注销异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
}
