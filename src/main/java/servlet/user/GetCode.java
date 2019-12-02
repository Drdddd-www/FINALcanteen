package servlet.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GetCode {
    private static String url = "https://sduonline.cn/isduapi/api/auth/login/system";

    public static int get(String name, String password) throws Exception {
        final int MAX = 10;
        int time = 0;
        Document doc = null;
        while (time < MAX) {
            try {
                doc = Jsoup
                        .connect(url)
                        .ignoreContentType(true)
                        .ignoreHttpErrors(true)
                        .timeout(1000 * 30)
                        .data("u", name)
                        .data("p", password)
                        .post();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                time++;
            }
        }
        JSONObject jsonObject = JSON.parseObject(doc.text());
        return jsonObject.getInteger("code");
    }

}
