package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK); // http응답 코드 -> 200번때 결국 200넣는거랑 같음
        //response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // http응답 코드 -> 400번때 결국 200넣는거랑 같음

        //[response-headers]
        // Header내용 직접 세팅
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        //[Header 편의 메서드]
        //response.setHeader와 response. ...의 차이
        //response.setHeader의 경우는, 헤더의 name을 통해서"" 값을 지정한거라면,
        //response. ...의 경우는 response.getContentType메서드를 이용해서 직접 세팅. 편의성이 다르다고 생각하면 될듯

        //결국 둘다 헤더를 세팅하는 메서드
        //content(response);
        //cookie(response);
        redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");
    }
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2

        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        //위에꺼랑 같음
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;

        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        //위에꺼랑 같음
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        //위에꺼랑 같음
        response.sendRedirect("/basic/hello-form.html");
    }
}
