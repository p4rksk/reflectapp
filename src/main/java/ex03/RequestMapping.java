package ex03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//실행시 검사한다는 annotaion
@Target(ElementType.METHOD)
public @interface   RequestMapping {
    String uri(); //i = 아이덴티티 파일
}
