package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자에 Autowire를 자동으로 설정해준다.
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final MyLogger myLogger; //myLogger는 request스코프이기 때문에 서버 기동시에 해당 객체는 없어서 주입하며 에러가 난다.
    private final ObjectProvider<MyLogger> myLoggerProvider; //request스코프를 호출전에 미리 만들어 놓기 위해 provider를 같이 사용 하여 정의 한다.

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject(); //이 부분에서 myLogger객체는 request(서버 요청시) 단위로 생성이 된다. init() 호출!!
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller Test");
        logDemoService.logic("testID");

        return "OK!!";
    }
}
