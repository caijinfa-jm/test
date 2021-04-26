package cn.tedu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "ServletReg",urlPatterns = "/reg")
public class ServletReg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try(Connection conn = DBUtils.getConn()) {
            String sql = "insert into user values(null,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();
            response.setContentType("text/html;charset=utf8");
            PrintWriter pw = response.getWriter();
            pw.print("注册成功!");
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
