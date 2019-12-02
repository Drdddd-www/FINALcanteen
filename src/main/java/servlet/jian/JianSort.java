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
import java.util.List;


@WebServlet("/jianSort")
public class JianSort extends HttpServlet {
    private String PAGE = "page";
    private String FLAG = "flag";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Info info = null;
        int page = 0;
        int flag = 0;
        List<Jian> jianList = null;

        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }
        if (request.getParameter(FLAG) != null) {
            flag = Integer.parseInt(request.getParameter(FLAG));
        }


        if (flag == 1) {
            jianList = JianDao.sortRandomly(page);
        } else if (flag == 2) {
            jianList = JianDao.sortByDate(page);
        } else if (flag == 3) {
            jianList = JianDao.sortByNiceNum(page);
        }

        if (page == 0) {
            info = new Info(-2, MSG.SERVE_ERROR);
        } else if (jianList == null) {
            info = new Info(-1, MSG.SQL_ERROR);
        } else {
            info = new Info(0, MSG.SUCCESS, jianList);
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
