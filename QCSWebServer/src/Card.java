import java.sql.ResultSet;

public class Card {
//	������
	public static String creatCard(String cardid, String cardstats, int cardbalance, String cardusername, String carduserid) {
		String sql = "insert into `cardinfo`(cardid,cardstats,cardbalance,cardusername,carduserid) value(?,?,?,?,?)";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardid);
			dbUtil.preparedStatement.setString(2, cardstats);
			dbUtil.preparedStatement.setInt(3, cardbalance);
			dbUtil.preparedStatement.setString(4, cardusername);
			dbUtil.preparedStatement.setString(5, carduserid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("��������Ϣ�ɹ�");
				flag = "true";
			} else {
				System.out.println("�����쳣");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	��ѯ���
	public static int getbalance(String cardid) {
		int flag = -1;
		boolean p = false;
		String sql = "select * from `cardinfo` where cardid=?";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.preparedStatement.setString(1, cardid);
			ResultSet i = dbUtil.preparedStatement.executeQuery();
			p = i.first();
			if (p) {
				flag = i.getInt("cardbalance");
			}
			System.out.println("���:" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	��ȡ��״̬
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
			System.out.println("��״̬:" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	��ֵ��
	public static String rechargeCard(String cardid, int cardbalance) {
		String sql = "UPDATE cardinfo SET cardbalance = ? WHERE cardid = ?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setInt(1, cardbalance);
			dbUtil.preparedStatement.setString(2, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				flag = "true";
			} else {
				System.out.println("��ֵ�쳣");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	�޸Ŀ�״̬
	public static String restatsCard(String cardid, String cardstats) {
		String sql = "UPDATE cardinfo SET cardstats = ? WHERE cardid = ?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardstats);
			dbUtil.preparedStatement.setString(2, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.printf("�޸ĳɹ�����״̬Ϊ��",cardstats);
				flag = "true";
			} else {
				System.out.println("�޸Ŀ�״̬�쳣");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
	
//	����������
	public static String createDeal(String cardid, int cardbalance,String cardid2,int cardbalance2) {
		String sql = "UPDATE cardinfo SET cardbalance = ? WHERE cardid = ?";
		String sql2 = "UPDATE cardinfo SET cardbalance = ? WHERE cardid = ?";
		DBUtil dbUtil = new DBUtil(sql);
		DBUtil dbUtil2 = new DBUtil(sql2);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setInt(1, cardbalance);
			dbUtil.preparedStatement.setString(2, cardid);
			dbUtil2.preparedStatement.setInt(1, cardbalance);
			dbUtil2.preparedStatement.setString(2, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			int i2 = dbUtil2.preparedStatement.executeUpdate();
			if (i > 0&&i2 > 0) {
				flag = "true";
			} else {
				System.out.println("��ֵ�쳣");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
			DBUtil.close(null, dbUtil2.preparedStatement, dbUtil2.connection);
		}
		return flag;
	}
	
//	ע����
	public static String deleteCard(String cardid) {
		String sql = "delete from `cardinfo` where cardid=?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.printf("ע���ɹ�");
				flag = "true";
			} else {
				System.out.println("ע���쳣");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
}
