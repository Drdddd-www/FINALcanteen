package servlet.dish;

import com.alibaba.fastjson.JSON;
import dao.DishDao;
import entity.Dish;
import util.Info;
import util.MSG;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dishShow")
public class DishShow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dish_name = "";
        Dish dish = null;
        if (request.getParameter("dish_name") != null) {
            dish_name = request.getParameter("dish_name");
        }
        dish = DishDao.selectByName(dish_name);
        Info info = null;

        if (dish == null) {
            info = new Info(-1, MSG.SQL_ERROR);
        } else {
            info = new Info(0, MSG.SUCCESS, dish);
        }


        String dishes = JSON.toJSONString(info);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,accept,token");
        PrintWriter out = response.getWriter();
        out.write(dishes);
        out.flush();

    }

}
