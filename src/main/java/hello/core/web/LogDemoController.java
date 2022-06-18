package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자에 Autowire를 자동으로 설정해준다.
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //myLogger는 request스코프이기 때문에 서버 기동시에 해당 객체는 없어서 주입하며 에러가 난다.

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller Test");
        logDemoService.logic("testID");

        return "OK!!";
    }
}
