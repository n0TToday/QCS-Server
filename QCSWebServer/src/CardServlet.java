
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CardServlet")
public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter printWriter = response.getWriter();
		String method = request.getParameter("method");
		String userid = request.getParameter("userid");
		String cardid;
		String cardstats;
		int cardbalance;
		int rechargenum;
		int nowbalance;
		String cardusername;
		String carduserid;
		String nowstats;
		String thing;
		String temp;
		switch (method) {
//      创建卡
		case "creatCard":
			cardid = request.getParameter("cardid");
			cardstats = "1";
			cardbalance = 0;
			cardusername = request.getParameter("cardusername");
			carduserid = request.getParameter("carduserid");
			temp = Card.creatCard(cardid, cardstats, cardbalance, cardusername, carduserid);
			
			if (temp == "true") {
				printWriter.write("true");
				Log.creatLog(userid, method, cardid,cardusername, temp);
			} else {
				printWriter.write("false");
			}
			
			break;

//		充值
		case "rechargeCard":
			cardid = request.getParameter("cardid");
			rechargenum = Integer.parseInt(request.getParameter("rechargenum"));
			nowbalance = Card.getbalance(cardid);
			cardbalance = nowbalance + rechargenum;
			temp = Card.rechargeCard(cardid, cardbalance);
			if (temp == "true") {
				nowbalance = Card.getbalance(cardid);
				printWriter.write(Integer.toString(nowbalance));
				System.out.println("充值成功,充值后金额为:"+ nowbalance);
				Log.creatLog(userid, method, cardid, Integer.toString(rechargenum),temp);
			} else {
				printWriter.write("false");
			}
			
			break;

//		修改卡状态（挂失）
		case "restatsCard":
			cardid = request.getParameter("cardid");
			nowstats = Card.getstats(cardid);
//			如果卡状态为正常
			if (nowstats == "1") {
				cardstats = "2";
				temp = Card.restatsCard(cardid, cardstats);
				if (temp == "true") {
					printWriter.write("true");
					Log.creatLog(userid, method, cardid, cardstats,temp);
				} else {
					printWriter.write("false");
				}
				
			}else if (nowstats == "2") {
				cardstats = "1";
				temp = Card.restatsCard(cardid, cardstats);
				if (temp == "true") {
					printWriter.write("true");
					Log.creatLog(userid, method, cardid, cardstats,temp);
				} else {
					printWriter.write("false");
				}
				
			}
			
			break;
			
//		注销卡
		case "deleteCard":
			cardid = request.getParameter("cardid");
			temp = Card.deleteCard(cardid);
			if (temp == "true") {
				printWriter.write("true");
				Log.creatLog(userid, method, cardid, null,temp);
			} else {
				printWriter.write("false");
			}
			
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
