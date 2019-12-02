package servlet.jian;

import com.alibaba.fastjson.JSON;
import dao.JianDao;
import entity.Jian;
import util.Info;
import util.MSG;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/jianChangeNiceNum")
public class JianChangeNiceNum extends HttpServlet {
    private String NAME = "name";
    private String FLAG = "flag";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Info info = null;
        boolean bool = false;
        try {
            String jian_name = "";
            int flag = -1;

            if (request.getParameter(NAME) != null) {
                jian_name = request.getParameter(NAME);
            }
            if (request.getParameter(FLAG) != null) {
                flag = Integer.parseInt(request.getParameter(FLAG));
            }

            Jian jian = JianDao.selectByName(jian_name);

            if (flag == 1) bool = JianDao.addNiceNum(jian);
            else if (flag == 2) bool = JianDao.reduceNiceNum(jian);


            if (bool) {
                info = new Info(0, MSG.SUCCESS, jian);
            } else {
                info = new Info(-1, MSG.SQL_ERROR);
            }


            String jians = JSON.toJSONString(info);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,accept,token");
            PrintWriter out = response.getWriter();
            out.write(jians);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
