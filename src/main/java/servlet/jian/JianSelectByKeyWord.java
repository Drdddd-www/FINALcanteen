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


@WebServlet("/jianSelectByKeyWord")
public class JianSelectByKeyWord extends HttpServlet {

    private String KEYWORD = "keyword";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码
        request.setCharacterEncoding("UTF-8");
        String keyWord = "";
        if (request.getParameter(KEYWORD) != null) {
            keyWord = request.getParameter(KEYWORD);
        }


        List<Jian> jianList = JianDao.selectByKeyWord(keyWord);
        Info info = null;

        if (jianList == null) {
            info = new Info(-1, MSG.SQL_ERROR);
        } else if (jianList.size() == 0) {
            info = new Info(1, MSG.NONE_DISH, jianList);
        } else {
            info = new Info(0, MSG.SUCCESS, jianList);
        }


        String jianes = JSON.toJSONString(info);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,accept,token");
        PrintWriter out = response.getWriter();
        out.write(jianes);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
