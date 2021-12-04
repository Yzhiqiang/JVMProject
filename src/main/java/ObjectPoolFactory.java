import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 14:38 2021/12/4
 * @Modified By:
 */
public class ObjectPoolFactory {
    private String message;


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
            objectpool.put(name, createObject(properties.getProperty(name)));
        }
    }
    public Object getObject(String name) {
        return objectpool.get(name);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        ObjectPoolFactory op = new ObjectPoolFactory();
        op.initpool("D:\\编程\\java\\JVMProject\\src\\main\\java\\obj.txt");
        System.out.println(op.getObject("a"));
        System.out.println(op.getObject("b"));
    }
}
