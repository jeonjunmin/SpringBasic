package beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplcationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류")
    void findBeanByParentTypeDuplicate(){
        //에러가 나올때 정상 실행하는 함수
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class ,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 빈 이름으로 지정하면 된다.")
    void findBeanByParentTypeByBeanName(){
        DiscountPolicy discountPolicy = ac.getBean("rateDiscoount", DiscountPolicy.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        DiscountPolicy discountPolicy = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 조회 모두 조회")
    void findAllBeanByParentType(){
        Map<String,DiscountPolicy > beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +"   value = " + beansOfType.get(key));
        }

    }

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscoount(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscount(){
            return new FixDiscountPolicy();
        }

    }

}
