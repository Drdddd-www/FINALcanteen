package servlet.jian;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;


@WebServlet("/uploadPic")
public class UploadPic extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建一个解析器工厂
        request.setCharacterEncoding("UTF-8");

        DiskFileItemFactory factory = new DiskFileItemFactory();
        //文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 判断enctype属性是否为multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            try {
                //解析请求，将表单中每个输入项封装成一个FileItem对象
                List<FileItem> fileItems = upload.parseRequest(request);
                // 迭代表单数据
                for (FileItem fileItem : fileItems) {
                    //判断输入的类型是 普通输入项 还是文件
                    if (fileItem.isFormField()) {
                        //普通输入项 ,得到input中的name属性的值,fileItem.getFieldName()
                        ////得到输入项中的值,fileItem.getString("UTF-8"),"UTF-8"防止中文乱码
                        System.out.println(fileItem.getFieldName() + "\t" + fileItem.getString("UTF-8"));
                    } else {
                        //上传的是文件，获得文件上传字段中的文件名
                        //注意IE或FireFox中获取的文件名是不一样的，IE中是绝对路径，FireFox中只是文件名
                        String fileName = fileItem.getName();
                        System.out.println(fileName);
                        //Substring是字符串截取，返回值是一个截取后的字符串
                        //lastIndexOf(".")是从右向左查,获取.之后的字符串
                        String ext = fileName.substring(fileName.lastIndexOf("."));
                        //UUID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法, UUID的唯一缺陷在于生成的结果串会比较长
                        String name = System.currentTimeMillis() + ext;
                        //将FileItem对象中保存的主体内容保存到某个指定的文件中
                        File file = new File("D:\\aTomcat\\apache-tomcat-9.0.29-windows-x64\\apache-tomcat-9.0.29\\webapps\\picture\\" + name);

                        //
                        //看这里
                        //注意看这里！！！！上面的路径要改为服务器Tomcat的webapps下的目录

                        fileItem.write(file);

                        HttpSession tu = request.getSession();
                        tu.setMaxInactiveInterval(60 * 3);
                        tu.setAttribute("tu", name);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("普通表单");
        }
    }

}
