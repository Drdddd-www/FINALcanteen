package servlet.canteen;

import com.alibaba.fastjson.JSON;
import dao.CanteenDao;
import entity.OpinionToCanteen;
import entity.User;
import util.Info;
import util.MSG;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class JudgeCanteen extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        double newScore = Double.parseDouble(request.getParameter("newScore"));
        Info info = null;
        boolean x;

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;//*********emmm,why add 1?*********

        User user = new User();
        user.setMonth(month);
        user.setPassword("");
        user.setUserId(Long.parseLong((String) request.getSession().getAttribute("userId")));
        x = CanteenDao.judge(user);
        if (x) {
            int oldMonth = CanteenDao.selectById(user.getUserId());
            if (oldMonth != user.getMonth()) {
                CanteenDao.updateById(user);
                CanteenDao.calculateScore(newScore);
                info = new Info(0, MSG.SUCCESS, new OpinionToCanteen().getScore());
            } else
                info = new Info(1, MSG.REPEAT);
        } else {
            CanteenDao.insert(user);
            info = new Info(0, MSG.SUCCESS, new OpinionToCanteen().getScore());
        }

        String score = JSON.toJSONString(info);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,accept,token");
        PrintWriter out = response.getWriter();
        out.write(score);
        out.flush();
    }

}
