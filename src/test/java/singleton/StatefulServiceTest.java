package singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void satefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(Testconfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000 주문
        statefulService1.order("UserA", 10000);
        //Threadㅠ : B사용자 10000 주문
        statefulService2.order("UserB", 20000);

        int price = statefulService1.getPrice();
        //객체를 싱글톤으로 공유하기때문에 20000으로 엎어쳐졌다.
        System.out.println("@@price = " + price);


    }

    static class Testconfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
