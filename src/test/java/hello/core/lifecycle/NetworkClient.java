package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String message){
        System.out.println("call: "+url+" message = " + message);
    }
    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close = " + url);
    }


    //빈이 등록되면서 컨테이너가 올라갈때 자동으로 호출
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NeworkClient init");
        connect();
        call("초기화 연결 메시지");
    }

    //컨테이너가 클로즈 되면 자동으로 호출
    @PreDestroy
    public void closed() throws Exception {
        System.out.println("NeworkClient close");
        disconnect();
    }
}
