package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    void AutoWiredOption () {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TetsBean.class);
    }

    static class  TetsBean{

        //Member클래스는 스프링 설정이(@Component어노테이션이 없다.) 되었지 않은 클래스여서 자동 주입이 되지 않는다. 따라서 Autowired옵션을 준 메소드의 필드값이 Member이면 에러가 난다.

        @Autowired(required = false)  //reuired=false옵션을 넣으면 해당 메소드는 호출 자체가 안된다.
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ //@Nullable 호출은 되지만 Member객체를 Null로 리턴
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){  //Optional<>옵션은 호출은 되지만 Member객체를 Optional.empty를 리턴
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
