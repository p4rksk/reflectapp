package ex03;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void findUrl(List<Object> instances, String path){

        for (Object instance : instances){
            Method[] methods = instance.getClass().getDeclaredMethods();

            for(Method method : methods){
                RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);

                if(rm == null) continue;

                if(rm.uri().equals(path)){
                    try {
                        method.invoke(instance); // == con.login();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 스캔해서 @Controller를 모두 찾음
    public static List<Object> componentScan(String pkg) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); // 패키지를 분석
        URL packageUrl = classLoader.getResource(pkg); // 어떤 패키지를 할건지 선택

        File dir = new File(packageUrl.toURI()); // 지정한 ex03 폴더를 가져온다

        List<Object> instances = new ArrayList<>(); // 뭐가 들어올지 모르기에 Object 자료형
        for (File file : dir.listFiles()){ // dir.list Files() -> ex03이 들고 있는 모든 파일을 들고 와서 for문을 돈다 (자기 경로만 체크) 실질적으로 out의 실행파일을 가져옴
            //System.out.println(file.getName());
            if(file.getName().endsWith(".class")){ // endsWith -> 파일의 끝이름
                String className = pkg + "." + file.getName().replace(".class",""); // 이름을 이렇게 만들어야 new할 수 있다.
                //System.out.println(className);

                Class cls = Class.forName(className); // new 하기
                if(cls.isAnnotationPresent(Controller.class)){ // 파일에 class에 @Controller가 있으면 true
                    Object instance = cls.newInstance();
                    instances.add(instance); // UserController, BoardController
                }

            }
        }
        return instances;
    }

    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Object> instances = componentScan("ex03");
        findUrl(instances, "/login");
    }
}