package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //HTTP 요청당 하나씩 생성이 되고 사라진다. proxyMode를 쓰면 provider를 안써도 가짜 클래스를 만들어 request 요청전에 미리 주입을 시켜논다.
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"]"+message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create:"+ this);
    }

    @PreDestroy
    public void close() {
        System.out.println("["+uuid+"] request scope bean close:"+ this);
    }
}
