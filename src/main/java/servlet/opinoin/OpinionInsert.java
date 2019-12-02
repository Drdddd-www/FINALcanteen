package servlet.opinoin;

import com.alibaba.fastjson.JSON;
import dao.OpinionDao;
import entity.Opinion;
import util.Info;
import util.MSG;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;


@WebServlet("/opinionInsert")
public class OpinionInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Info info = null;
        try {
            request.setCharacterEncoding("UTF-8");

            int dishId = 0;
            String userId = "";
            String msg = "";

            if (request.getParameter("dishId") != null) {
                dishId = Integer.parseInt(request.getParameter("dishId"));
            }
            if (request.getParameter("msg") != null) {
                msg = request.getParameter("msg");
            }

            userId = (String) request.getSession().getAttribute("userId");

            Opinion opinion = new Opinion();
            opinion.setDishId(dishId);
            opinion.setUserId(Long.parseLong(userId));
            opinion.setOpinionMsg(msg);


            if (OpinionDao.insert(opinion)) {
                info = new Info(0,MSG.SUCCESS);
            } else {
                info = new Info(-1, MSG.SQL_ERROR);
            }

            String opinions = JSON.toJSONString(info);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,accept,token");
            PrintWriter out = response.getWriter();
            out.write(opinions);
            out.flush();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
