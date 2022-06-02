package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        //컴포넌트 스캔은 이름 그대로 @Component 어노테이션이 붙은 클래스를 스캔해 스프링빈에 등록한다.
        //컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 빈컨테이너에 등록되기 때문에 excludeFilters를 이용해서 Configuration을 컴포넌트 스캔대상에서 제외한다.
        excludeFilters = @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes = Configuration.class)
        //빈컨테이너에 등록할 컴포넌트의 스캔 시작점을 설정할 수 있다. 기본은 Appconfig파일 위치 기준패키지의 하위를 다 뒤진다.
//        ,basePackages = "hello.core.member"

)
public class AutoAppConfig {


}
