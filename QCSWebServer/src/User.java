import java.sql.ResultSet;

public class User {
	// 注册
	public static String regist(String userid, String username, String userpwd, String registdate) {
		System.out.printf(userid, username, userpwd);
		String sql = "insert into `userinfo`(userid,username,userpwd,registdate) value(?,?,?,?)";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, userid);
			dbUtil.preparedStatement.setString(2, username);
			dbUtil.preparedStatement.setString(3, userpwd);
			dbUtil.preparedStatement.setString(4, registdate);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("注册成功");
				flag = "true";
			} else {
				System.out.println("注册异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}

	// 查找用户名并返回userid
	public static String login(String username) {
		String flag = "false";
		boolean p = false;
		String sql = "select * from `userinfo` where username=?";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.preparedStatement.setString(1, username);
			ResultSet i = dbUtil.preparedStatement.executeQuery();
			p = i.first();
			if (p) {
				flag = i.getString("userid");
			}
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}

	// 返回用户名
	public static String getusername(String userid) {
		String flag = "false";
		boolean p = false;
		String sql = "select * from `userinfo` where userid=?";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.preparedStatement.setString(1, userid);
			ResultSet i = dbUtil.preparedStatement.executeQuery();
			p = i.first();
			if (p) {
				flag = i.getString("username");
			}
			System.out.println("name:" + flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}

	// 查找密码
	public static String selectpwd(String userid, String userpwd) {
		String flag = "false";
		boolean p = false;
		String sql = "select * from `userinfo` where userid=?&&userpwd=?";
		DBUtil dbUtil = new DBUtil(sql);
		try {
			dbUtil.preparedStatement.setString(1, userid);
			dbUtil.preparedStatement.setString(2, userpwd);
			ResultSet i = dbUtil.preparedStatement.executeQuery();
			p = i.first();
			if (p) {
				flag = "true";
			}
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}

	// 修改密码
	public static String updatepwd(String userid, String userpwd) {
		System.out.printf(userid, userpwd);
		String sql = "UPDATE userinfo SET userpwd = ? WHERE userid = ?";
		DBUtil dbUtil = new DBUtil(sql);
		String flag = "false";
		try {
			dbUtil.preparedStatement.setString(1, userid);
			dbUtil.preparedStatement.setString(2, userpwd);
			int i = dbUtil.preparedStatement.executeUpdate();
			if (i > 0) {
				System.out.println("修改密码成功");
				flag = "true";
			} else {
				System.out.println("修改异常");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, dbUtil.preparedStatement, dbUtil.connection);
		}
		return flag;
	}
}
