package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //ConfigurableApplicationContext에서 제공하는 컨테이너 종료 메소드 close
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "closed") //스프링 컨테이너가 시작하면서 NetworkClient에 DI가 일어날때 NetworkClient클래스의 init 메소드가 호출되고 스프링이 꺼지면서 closed 메소드가 호출된다.
        public NetworkClient networkClient (){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

}
