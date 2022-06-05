package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("MainDiscountPolicy")  //각 클래스에 @MainDiscountPolicy를 붙이면 @Qualifier("MainDiscountPolicy")를 쓰는 것과 같은 효과를 지닌다.
public @interface MainDiscountPolicy {
}


