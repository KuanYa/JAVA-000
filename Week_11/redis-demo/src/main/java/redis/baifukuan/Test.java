package redis.baifukuan;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        String a = "没人";
        String b = "比我";
        String c = "更懂";
        String d = "java";
        String s = a + b + c + d;

        System.out.println((a+b) == ("没人" + "比我"));
        System.out.println(a + b + c);
        System.out.println((a + b + c + d) == s);
        // String json="{\"a\":{\"b\":[\"v\",2,{\"c\":0}]},\"d\":[1,null,3]}";
        // HashMap<String, Object> hashMap = new HashMap<>();
        // HashMap<String, Object> hashMap1 = new HashMap<>();
        // HashMap<String, Object> hashMap2 = new HashMap<>();
        // Object[] objects={1,null,3};
        // hashMap1.put("c",0);
        // Object[] objects1={"v",2,hashMap1};
        // hashMap2.put("b",objects1);
        // hashMap.put("a",hashMap2);
        // hashMap.put("d",objects);
    }


    class Inner {
        public String  v1 = "Fake News";
        public String v2 = "Go ahead";
    }

    private static String GetVal() {
        try {
            return Inner.class.newInstance().v1;
        } catch (Exception e) {
            try {
                return Inner.class.getDeclaredConstructor(Test.class).newInstance((Test)null).v2;
            } catch (Exception ee) {
                ee.printStackTrace();
                return "Fake News, Go ahead";
            }
        }
    }
    private static volatile int s = 0;
    private static final ThreadPoolExecutor async = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<>());

    public static boolean searchMatrix(int[][] nums, int x) {

        return false;
    }
    static class StrObject{
        String str;
        public StrObject(String str){
            this.str = str;
        }
        public void setStr(String str){
            this.str = str;
        }
        @Override
        public String toString() {
            return str;
        }
    }
    private static Integer method(Integer i){
        try{
            if(i++ > 0)
                throw new IOException();
            return i++;
        }catch (IOException e){
            i++;
            return i++;
        }catch (Exception e){
            i++;
            return i++;
        }finally {
            return i++;
        }
    }
}
