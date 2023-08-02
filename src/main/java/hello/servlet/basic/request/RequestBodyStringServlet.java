package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//바이트에서 문자로 변환시키는 경우에는, 해당 stream 바이트를 인코딩 정보를 알려줘야함.
                                                                                        //inputStream -> 바이트 스트림 , StandardCharsets.UTF_* -> 인코딩 정보
        System.out.println("messageBody = " + messageBody); //postman프로그램에서 post에 hello!라는 바디 내용을 context/type -> plain/text인 형식으로 /request-body-string으로 보냄

        response.getWriter().write("ok"); //응답시 반환 받을 문자
    }
}
