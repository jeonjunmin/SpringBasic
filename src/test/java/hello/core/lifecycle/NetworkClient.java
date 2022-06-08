package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

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


    //객체가 생성되면서 주입이 되면 아래 afterPropertiesSet 함수가 호출이 되고 스프링이 죽으면서 destroy 함수가 호출된다.

    public void init() throws Exception {
        System.out.println("NeworkClient init");
        connect();
        call("초기화 연결 메시지");
    }

    public void closed() throws Exception {
        System.out.println("NeworkClient close");
        disconnect();
    }
}
