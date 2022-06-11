package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class singTonBeanFind {

    @Test
    void singletoneBeanFind(){
        //아래 시점에 init
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(singltonBean.class);
        System.out.println("find1");
        singltonBean singltonBean1 = ac.getBean(singltonBean.class);
        System.out.println("find2");
        singltonBean singltonBean2 = ac.getBean(singltonBean.class);
        System.out.println("find3");
        System.out.println("singltonBean1 = " + singltonBean1);
        System.out.println("singltonBean2 = " + singltonBean2);

        Assertions.assertThat(singltonBean1).isSameAs(singltonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class singltonBean {

        @PostConstruct
        public void init(){
            System.out.println("singltonBean init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("singltonBean destroy");
        }


    }
}
