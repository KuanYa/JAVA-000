package java0.conc0303;

import java.util.Queue;
import java.util.concurrent.*;

public class Test<V> {

    public static void main(String[] args) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "null";
            }
        };
        // int corePoolSize,
        // int maximumPoolSize,
        // long keepAliveTime,
        // TimeUnit unit,
        // BlockingQueue<Runnable> workQueue

        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
        ExecutorService executorService = new ThreadPoolExecutor(2,4,10000,TimeUnit.MICROSECONDS,workQueue);
        for(int i=0;i<10;i++){
            FutureTask<String> result = (FutureTask<String>) executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "sout" ;
                }
            });
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void callable() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        }).get();
    }
}
