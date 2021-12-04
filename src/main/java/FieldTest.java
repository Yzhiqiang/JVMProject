import java.lang.reflect.Field;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 19:03 2021/12/4
 * @Modified By:
 */
public class FieldTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Person p = new Person();
        Class<Person> personClazz = Person.class;
        Field namefield = personClazz.getDeclaredField("name");   // 取得该字段
        System.out.println(namefield);
        namefield.setAccessible(true);
        namefield.set(p, "lucy");

        Field agefield = personClazz.getDeclaredField("age");
        System.out.println(agefield);
        agefield.setAccessible(true);
        agefield.set(p, 200);
        System.out.println(p);
    }
}
