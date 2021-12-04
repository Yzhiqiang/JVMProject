import java.lang.reflect.Field;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 14:25 2021/12/4
 * @Modified By:
 */
public class Main {
    public static void main(String[] args) {
        Class<testFS> clazz = testFS.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }
}
