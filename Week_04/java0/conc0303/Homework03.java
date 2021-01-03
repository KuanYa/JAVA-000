package java0.conc0303;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03 {

    private static final int THREAD_COUNT = 4;
    // 在这里创建一个线程或线程池，
    private static final ExecutorService executorService = Executors.newFixedThreadPool(7);

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        int result = 0;
        // result = future();
        // result = countDownLatch();
        // result = completableFuture();
        // result = join();
        // result = semaphore();
        result = cyclicBarrier();
        result = futureTask();
        Object object = new Object();

        // 为什么会先执行这里
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        System.out.println("主线程执行结束");
        // 关闭线程池
        executorService.shutdown();
    }

    /**
     * 方法一，使用线程池异步获取
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static Integer future() throws ExecutionException, InterruptedException {
        int result = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum(36);
            }
        }).get();
        return result;
    }

    /**
     * 使用CountDownLatch 获取
     * 注意每次一定要中countDown()方法,否则程序不会正常执行完成，会一直阻塞
     *
     * @return
     * @throws InterruptedException
     */
    public static Integer countDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        AtomicInteger result = new AtomicInteger(1);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("线程池获取数据。。。。。");
                    result.set(sum(36));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        return result.get();
    }

    /**
     * 异步编程获取
     *
     * @return
     */
    public static int completableFuture() {
        return CompletableFuture.supplyAsync(() -> {
            return sum(36);
        }).join();
    }

    /**
     * 两个线程交替执行
     *
     * @return
     * @throws InterruptedException
     */
    public static int join() throws InterruptedException {
        AtomicInteger result = new AtomicInteger();
        Thread thread1 = new Thread(() -> {
            result.set(sum(36));
            System.out.println(Thread.currentThread().getName() + sum(36));
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + sum(36));
        });
        thread1.setName("线程1：");
        thread2.setName("线程2：");
        thread1.start();
        thread2.start();
        thread1.join();
        return result.get();
    }

    /**
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int semaphore() throws ExecutionException, InterruptedException {
        int result = 0;
        // 设置只允许运行线程的个数为1个
        /**
         * semaphore
         * 当第T1执行进入时，执行acquire(),信号量里面的计数器值减一，大于等于0，所以T1可以执行
         * 当T2进入时，执行acquire()，信号量里面的计数器值减一，小于0，所以T2线程阻塞
         * 当T1执行release()时，计数器加一，大于等于0，T2线程被唤醒，可以执行
         *
         */
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < THREAD_COUNT; i++) {
            result = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    semaphore.acquire();
                    System.out.println("获取执行结果。。。。。");
                    int result2 = sum(36);
                    semaphore.release();
                    return result2;
                }
            }).get();
        }
        return result;
    }

    /**
     * 使用CyclicBarrier
     * CyclicBarrier(int parties) 构造函数里面的参数意思是，有几个线程被阻塞住，等待最后一起执行
     * 例如，当有设置为2时，有两个线程被阻塞住，然后执行对应业务后，将返回的Future保存下来，最后在主线程中执行获取值的操作
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int cyclicBarrier() throws ExecutionException, InterruptedException {
        int result = 0;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println(sum(36));
            }
        });
        List<Future> resultFuture = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Future<Integer> object = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    cyclicBarrier.await();
                    return sum(36);
                }
            });
            resultFuture.add(object);
        }
        return (int) resultFuture.get(0).get();
    }

    /**
     * FutureTask
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static int futureTask() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum(36);
            }
        });
        return (Integer) executorService.submit(futureTask).get();
    }

    private static int sum(int num) {
        return fibo(num);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
