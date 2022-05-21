package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인대상 금액액
     * */

    int discouunt(Member member, int price);
}
