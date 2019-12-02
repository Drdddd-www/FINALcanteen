package servlet.user;

import com.alibaba.fastjson.JSON;
import util.Info;
import util.MSG;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class Login extends HttpServlet {
    String userId = "";
    String password = "";
    Info info = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            if (request.getParameter("userId") != null && request.getParameter("password") != null) {
                userId = request.getParameter("userId");
                password = request.getParameter("password");
            } else {
                info = new Info(1, MSG.SPACE);
            }


            if (GetCode.get(userId, password) == 0) {
                Cookie u = new Cookie("userId", userId);
                Cookie p = new Cookie("password", password);
                u.setMaxAge(2 * 60);
                p.setMaxAge(2 * 60);
                response.addCookie(u);
                response.addCookie(p);
                request.getSession().setAttribute("userId", userId);
                info = new Info(0, MSG.SUCCESS);
            } else {
                info = new Info(2, MSG.PASSWORD_ERROR);
            }


            String userId = JSON.toJSONString(info);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,accept,token");
            PrintWriter out = response.getWriter();
            out.write(userId);
            out.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
