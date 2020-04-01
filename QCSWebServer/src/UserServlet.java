
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter printWriter = response.getWriter();
		String method = request.getParameter("method");
		String userid;
		String username;
		String userpwd;
		String stats;
		String newpwd;
		String registdate;
		String temp;

		switch (method) {
//            注册
		case "regist":
			userid = request.getParameter("userid");
			username = request.getParameter("username");
			userpwd = request.getParameter("userpwd");
			registdate = request.getParameter("registdate");
			temp = User.login(username);
			if (temp == "false") {
				temp = User.regist(userid, username, userpwd, registdate);
				if (temp == "true") {
					System.out.println("注册成功");
					printWriter.write("true");
					Log.creatLog(null, method, username, userpwd, temp);
				} else {
					System.out.println("注册失败");
					printWriter.write("false");
				}
				
			} else {
				System.out.println("用户名已被注册");
				printWriter.write("usernamefalse");
			}

			break;

//            登录
		case "login":
			username = request.getParameter("username");
			userpwd = request.getParameter("userpwd");
			userid = User.login(username);
			if (userid != "false") {
				stats = User.selectpwd(userid, userpwd);
				if (stats != "false") {
					temp = "true";
					printWriter.write(userid);
					Log.creatLog(userid, method, username, null, temp);
				} else {
					temp = "pwdfalse";
					printWriter.write("pwdfalse");
				}
				
			} else {
				printWriter.write("usernamefalse");
			}
			
			break;

//            查找用户名
		case "getusername":
			userid = request.getParameter("userid");
			username = User.getusername(userid);
			if (username != "false") {
				printWriter.write(username);
			} else {
				printWriter.write("false");
			}
			break;

//            检查用户名重复
		case "checkusername":
			username = request.getParameter("username");
			stats = User.login(username);
			if (stats != "false") {
				printWriter.write("false");
			} else {
				printWriter.write("true");
			}
			break;

//            修改密码
		case "updatepwd":
			userid = request.getParameter("userid");
			userpwd = request.getParameter("oldpwd");
			newpwd = request.getParameter("newpwd");
			temp = User.selectpwd(userid, userpwd);
			if (temp != "false") {
				temp = User.updatepwd(userid, newpwd);
				if (temp != "false") {
					printWriter.write("true");
					Log.creatLog(userid, method, userpwd, newpwd, temp);
				} else {
					printWriter.write("updatepwdfalse");
				}
			} else {
				printWriter.write("oldpwdfalse");
			}
			break;
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
