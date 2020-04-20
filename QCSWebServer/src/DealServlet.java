
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/DealServlet")
public class DealServlet extends HttpServlet {
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
		String dealsource;
		String dealdata;
		String dealperson;
		String searchtype;
		String searchdata;
		switch (method) {

//		-1:交易卡状态异常
//		-2:交易卡余额不足
//		-3/-4:交易失败
//		1:交易成功
		
		case "insertdeal":
			dealsource = request.getParameter("dealsource");
			int dealtempdata = Integer.parseInt(request.getParameter("dealdata"));
			dealdata = request.getParameter("dealdata");
			dealperson = request.getParameter("dealperson");
			if (Card.getstats(dealperson) == "1") {
				if ((Card.getbalance(dealperson) - dealtempdata) >= 0) {
					if (Card.createDeal(dealperson, Card.getbalance(dealperson) - dealtempdata, dealsource,
							Card.getbalance(dealsource) + dealtempdata) == "true") {
						String temp = Deal.createDeal(dealsource, dealdata, dealperson);
						if (temp == "true") {
							printWriter.write("1");
							Log.creatLog(userid, method, dealperson, dealdata, dealsource);
						} else {
							printWriter.write("-4");
						}

					} else {
						printWriter.write("-3");
					}

				} else {
					printWriter.write("-2");
				}

			} else {
				printWriter.write("-1");
			}

			break;

		case "loaddeal":
			searchtype = request.getParameter("searchtype");
			searchdata = request.getParameter("searchdata");
			List<LoadDeal> list = Deal.loaddeal(searchtype, searchdata);
			String json = new Gson().toJson(list);
			printWriter.print(json);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
