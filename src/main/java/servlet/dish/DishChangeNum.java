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


@WebServlet("/dishChangeNum")
public class DishChangeNum extends HttpServlet {
    private String NAME = "name";
    private String FLAG = "flag";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码
        request.setCharacterEncoding("UTF-8");
        Info info = null;
        boolean bool = false;
        int flag = -1;
        try {
            String dish_name = "";
            if (request.getParameter(NAME) != null) {
                dish_name = request.getParameter(NAME);
            }
            if (request.getParameter(FLAG)!=null){
                flag=Integer.parseInt(request.getParameter(FLAG));
            }

            Dish dish = DishDao.selectByName(dish_name);
            if (flag==1){
                bool=DishDao.addNiceNum(dish);
            }else if (flag==2){
                bool=DishDao.reduceNiceNum(dish);
            }else if (flag==3){
                bool=DishDao.addBadNum(dish);
            }else if (flag==4){
                bool=DishDao.reduceBadNum(dish);
            }



            if (bool) {
                info = new Info(0,MSG.SUCCESS,dish);
            } else {
                info = new Info(-1, MSG.SQL_ERROR);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
