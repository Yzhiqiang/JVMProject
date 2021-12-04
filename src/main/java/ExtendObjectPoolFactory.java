import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 18:37 2021/12/4
 * @Modified By:
 */
public class ExtendObjectPoolFactory{

    private Map<String, Object> objectpool = new HashMap<>();


    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.getConstructor().newInstance();   //实例化一个实例
    }
    public void initpool(String filename) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        FileInputStream in = new FileInputStream(filename);
        Properties properties = new Properties();
        properties.load(in);
        for (String name : properties.stringPropertyNames()) {
            if(!name.contains("%")) {
                objectpool.put(name, createObject(properties.getProperty(name)));
            } else {
                String[] ObjAndProp = name.split("%");
                Object target = getObject(ObjAndProp[0]);
                String mtdname = "set"+ObjAndProp[1].substring(0,1).toUpperCase() + ObjAndProp[1].substring(1);
                Class<?> clazz = target.getClass();
                Method method = clazz.getMethod(mtdname, String.class);
                method.invoke(target, properties.getProperty(name));
            }
        }
    }
    public Object getObject(String name) {
        return objectpool.get(name);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        ExtendObjectPoolFactory op = new ExtendObjectPoolFactory();
        op.initpool("D:\\编程\\java\\JVMProject\\src\\main\\java\\obj.txt");
        System.out.println(op.getObject("a"));
    }
}
