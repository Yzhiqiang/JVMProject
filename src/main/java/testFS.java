import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 14:01 2021/12/4
 * @Modified By:
 */
public class testFS {
    private String password;
    public void Print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Class<testFS> clazz = testFS.class;    //实例化一个该类
        Constructor[] constructors = clazz.getDeclaredConstructors();//获取这个类的全部构造函数
        for (Constructor constructor : constructors) {
            System.out.println("全部构造函数：" + constructor);
        }
        Constructor[] constructors1 = clazz.getConstructors();
        for (Constructor constructor : constructors1) {
            System.out.println("public 的全部构造函数"+constructor);
        }
        try {
            System.out.println("方法为："+clazz.getMethod("Print", String.class).getParameterCount());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("该类的全部注解为："+annotation);
        }
    }
}
