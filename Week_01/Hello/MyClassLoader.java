import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义classLoader类加载器
 */
public class MyClassLoader extends ClassLoader {

    private static String path = MyClassLoader.class.getResource("/Hello.xlass").getPath();

    /**
     * 主方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        try {
            MyClassLoader myClassLoader = new MyClassLoader();
            Object hello = myClassLoader.findClass("Hello").newInstance();
            Method method = hello.getClass().getDeclaredMethod("hello");
            method.invoke(hello);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码
     *
     * @return
     */
    private byte[] decodeClass() {
        byte[] sourceByte = getClassArray(path);
        for (int i = 0; i < sourceByte.length; i++) {
            sourceByte[i] = (byte) (255 - sourceByte[i]);
        }
        return sourceByte;
    }

    /**
     * 重写 findClass 方法
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @author baifukuan
     */
    @Override
    protected Class<?> findClass(String name) {
        // 解码后的class字节码集合
        byte[] bytes = decodeClass();
        return defineClass(name, bytes, 0, bytes.length);
    }


    /**
     * 获取字节码数据
     *
     * @param path
     * @return
     */
    private static byte[] getClassArray(String path) {
        File file = new File(path);
        ByteArrayOutputStream out = null;
        try (FileInputStream in = new FileInputStream(file)) {
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int size = 0;
            while ((size = in.read(buffer)) != -1) {
                out.write(buffer, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }
}
