package ex02;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) {
        String path = "/updatePassword";

        UserController con =new UserController();

        Method[] methods = con.getClass().getDeclaredMethods(); //get 가져오다.
        //System.out.println(methods.length);

        for (Method method : methods){
            //System.out.println(method.getName());
            RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class); // getDeclared Annotation은 method에 어노테이션이 몇개 있는지 분석

            if (rm == null) {
                continue;
            }
            if (rm.uri().equals(path)){
                try {
                    method.invoke(con); //con.login();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
