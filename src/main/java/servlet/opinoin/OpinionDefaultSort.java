package servlet.opinoin;

import com.alibaba.fastjson.JSON;
import dao.OpinionDao;
import entity.Opinion;
import util.Info;
import util.MSG;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/opinionDefaultSort")
public class OpinionDefaultSort extends HttpServlet {
    private String DISHID = "dishId";
    private String PAGE = "page";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Info info = null;
        int dishId = 0;
        int page = 0;
        if (request.getParameter(DISHID) != null) {
            dishId = Integer.parseInt(request.getParameter(DISHID));
        }
        if (request.getParameter(PAGE) != null) {
            page = Integer.parseInt(request.getParameter(PAGE));
        }
        List<Opinion> opinionList = new ArrayList<>();
        opinionList=OpinionDao.defaultSort(page, dishId);

        if (dishId==0||page == 0) {
            info = new Info(-2, MSG.SERVE_ERROR);
        } else if (opinionList == null) {
            info = new Info(-1, MSG.SQL_ERROR);
        } else {
            info = new Info(0, MSG.SUCCESS, opinionList);
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
