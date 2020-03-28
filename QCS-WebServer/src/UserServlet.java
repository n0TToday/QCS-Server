import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter printWriter = response.getWriter();
        String method = request.getParameter("method");
        switch (method) {
//            注册
            case "regist":
                String userid = request.getParameter("userid");
                String username = request.getParameter("username");
                String userpwd = request.getParameter("userpwd");
                if (User.regist(userid, username, userpwd)) {
                    System.out.println("注册成功");
                    printWriter.write("true");
                } else {
                    System.out.println("注册失败");
                    printWriter.write("false");
                }

                break;

//            登录
            case "login":
                username = request.getParameter("username");
                userpwd = request.getParameter("userpwd");
                userid = User.login(username);
                if (userid != "false") {
                    String stats = User.selectpwd(userid, userpwd);
                    if (stats != "false") {
                        printWriter.write(userid);
                    } else {
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

//            修改密码
            case "updatepwd":
                userid = request.getParameter("userid");
                userpwd = request.getParameter("oldpwd");
                String newpwd = request.getParameter("newpwd");
                String stats = User.selectpwd(userid, userpwd);
                if (stats != "false") {
                    stats = User.updatepwd(userid, newpwd);
                    if (stats != "false") {
                        printWriter.write("true");
                    } else {
                        printWriter.write("updatepwdfalse");
                    }
                } else {
                    printWriter.write("oldpwdfalse");
                }
                break;
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doGet(request, response);
    }
}
