package singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SinglToneTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1.조회:호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2.조회:호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인. 객체마다 생성을 하면 메모리에 부하가 간다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);


    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void singleToneServiceTest () {
        SingleTonService singleTonService1 = SingleTonService.getInstance();
        SingleTonService singleTonService2 = SingleTonService.getInstance();
        //같은 객체 인스턴스가 반환이 된다.
        System.out.println("singleTonService1 = " + singleTonService1);
        System.out.println("singleTonService2 = " + singleTonService2);

        //memberService1 = memberService2
        assertThat(singleTonService1).isSameAs(singleTonService2);
    }


    @Test
    @DisplayName("빈컨테이너는 싱글톤 패턴을 제공한다.")
    void singleToneServiceTest2 () {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //같은 객체 인스턴스가 반환이 된다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 = memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }


}
