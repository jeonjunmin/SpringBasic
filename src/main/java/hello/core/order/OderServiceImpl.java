package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //아래 주석 된 생성자를 자동으로 생성해주는 롬복
//@Qualifier("mainDiscountPolicy") //@Qualifier("mainDiscountPolicy")가 붙어있는 객체를 주입시켜 준다.
public class OderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //생성자 의존관계 주입을 사용할때 final을 사용할 수 있다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

   @Autowired //생성자에 자동으로 의존관계를 주입시켜준다.
    public OderServiceImpl(MemberRepository memberRepository,
//                           @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy)
                            DiscountPolicy discountPolicy)
   {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        System.out.println("discountPolicy = " + discountPolicy);
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discouunt(member,itemPrice);


        return new Order(memberId , itemName, itemPrice, discountPrice);
    }
}
