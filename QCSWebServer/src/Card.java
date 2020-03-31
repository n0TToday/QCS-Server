import java.sql.ResultSet;

public class Card {
//	������
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
	public static String rechargeCard(String cardid, String cardbalance) {
		String sql = "UPDATE cardinfo SET cardbalance = ? WHERE cardid = ?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, cardbalance);
			dbUtil.preparedStatement.setString(2, cardid);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.printf("��ֵ�ɹ�,��ֵ����Ϊ",cardbalance);
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
	
//	ע����
	public static String deleteCard(String cardid) {
		String sql = "delete * from `cardinfo` where cardid=?";
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
