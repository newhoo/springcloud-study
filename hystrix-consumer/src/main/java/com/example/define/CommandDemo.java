package com.example.define;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * CommandDemo
 *
 * @author huzunrong
 * @since 1.1.1
 */
public class CommandDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        RandomCommand randomCommand = new RandomCommand();
        Future<String> future = executorService.submit(randomCommand::run);

        String result = null;
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            result = randomCommand.fallback();
        }
        System.err.println(result);

        executorService.shutdown();
    }

    static class RandomCommand implements Command<String> {

        @Override
        public String run() {
            Random random = new Random();
            int sleepTime = random.nextInt(150);
            System.err.format("调用耗时：%sms\n", sleepTime);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "服务调用成功";
        }

        @Override
        public String fallback() {
            return "服务降级了";
        }
    }
}