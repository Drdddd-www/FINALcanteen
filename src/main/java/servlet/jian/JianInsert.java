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
import java.sql.Date;

@WebServlet("/jianInsert")
public class JianInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Info info = null;
        try {
            request.setCharacterEncoding("UTF-8");

            String name = "";
            String msg = "";
            int niceNum = 0;

            if (request.getParameter("name") != null) {
                name = request.getParameter("name");
            }
            if (request.getParameter("msg") != null) {
                msg = request.getParameter("msg");
            }

            String userId=(String)request.getSession().getAttribute("userId") ;

            Jian jian = new Jian();
            jian.setUserId(Long.parseLong(userId));
            jian.setName(name);
            jian.setMsg(msg);
            jian.setNiceNum(niceNum);
            jian.setDate(new Date(System.currentTimeMillis()));
            jian.setTu((String) request.getSession().getAttribute("tu"));


                
            if (JianDao.insert(jian)) {
                info = new Info(null);
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