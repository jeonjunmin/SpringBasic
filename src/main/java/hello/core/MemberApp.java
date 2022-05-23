package hello.core;

import hello.core.member.Grade;

import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

//        MemberService memberService = new MemberServiceImpl(memberRepository);

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member joinmember = new Member(1L , "MemberA" , Grade.VIP);
        memberService.join(joinmember);

        Member findmember = memberService.findMember(1L);

        System.out.println("joinmember = " + joinmember.getName());
        System.out.println("findmember = " + findmember.getName());


    }


}
