package cc.yuerblog.demo.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class MyThreadPool {
    @Bean(name="defaultThreadPool")
    public ExecutorService defaultThreadPool(@Value("${myThreadPool.threadNum}") Integer numThread) {
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        return executor;
    }
}
