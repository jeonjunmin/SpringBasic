package beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;

import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 이름으로 조회")
   void findBeanByname(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(memberService.getClass());
    }

    @Test
    @DisplayName("모든 이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        
        Assertions.assertThat(memberService).isInstanceOf(memberService.getClass());
    }

    @Test
    @DisplayName("구현체 타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(memberService.getClass());
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByNameX(){
        //빈에 해당 이름(xxx)가 없어서 에러가 난다.
//        MemberService memberService = ac.getBean("xxxx", MemberServiceImpl.class);

        //에러가 나야 성공하는 함수 : () -> 에러코드
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class ,
                () -> ac.getBean("xxxx", MemberServiceImpl.class));
    }
}
