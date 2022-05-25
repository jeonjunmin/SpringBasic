package beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class applcationContextBeanTypeTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은타입이 둘 이상있으면 중복오류가 발생한다.")
    void findBeanByDuplicate(){
//        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        //에러가 나올때 정상 실행하는 함수
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class ,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은타입이 둘 이상있으면 빈 이름을 지정하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        MemberRepository memberRepository2 = ac.getBean("memberRepository2", MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회한다.")
    void findAllBeanByType(){

        Map <String, MemberRepository > beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "    value = " + beansOfType.get(key));
        }

    }


    //테스트용 컨피그 클래스
    @Configuration
    static class sameBeanConfig {
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

}
