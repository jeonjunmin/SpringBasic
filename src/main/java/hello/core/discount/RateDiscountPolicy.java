package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //@Component 옵션을 FixDiscountPolicy객체와 RateDiscountPolicy객체에 동시에 줬기 때문에 DiscountPolicy의 구현체 Bean컨테이너 생성시 충돌이 난다. 그래서 @Primary를 붙여 이 구현체를 우선 주입하겠다는 뜻이 된다.
//@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discouunt(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }else{
            return 0;
        }
    }
}
