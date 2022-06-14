package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class singletonWithPrototypeTest {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);


    }

    @Test
    void singletoneClientUseProtoType(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);
        //prototype을 사용하는 singlton을 사용하면 그 안의 prototype은 공유가 되어버린다. prototype의 의미가 퇴색되어버림
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean;

        @Autowired //ObjectProvider를 사용하여 prototypeBean을 싱글톤 안에서 그때그때 만들어 공유하지 않고 사용할 수 있다.
        private ObjectProvider<PrototypeBean> prototypeBeans;
//        private ObjectFactory<PrototypeBean> prototypeBeans;
//        private Provider<PrototypeBean> prototypeBeans;


//        @Autowired //생성 시점에 prototypeBean이 주입됨
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeans.getObject();
//            PrototypeBean prototypeBean = prototypeBeans.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount (){
            count++;
        }

        public int getCount (){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("ProtoTypeBean.init  "+ this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("ProtoTypeBean.destroy");
        }

    }

}
