package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                           //http요청이 오면 WAS가 (ServletContainer) request 객체를 만들어서 전달해줌
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        //request = org.apache.catalina.connector.RequestFacade@743ac2bc
        System.out.println("response = " + response);
        //response = org.apache.catalina.connector.ResponseFacade@7907146c

        String username = request.getParameter("username");
        //http://localhost:8080/hello?username=kim 여기에서, ?username = kim 으로 request값을 매개변수 username으로 받아온 kim 값 => username
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
