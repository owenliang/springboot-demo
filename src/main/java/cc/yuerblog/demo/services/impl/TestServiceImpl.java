package cc.yuerblog.demo.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

@Service("testService")
public class TestServiceImpl implements cc.yuerblog.demo.services.TestService {
    @Value("${testservice.prefix:hello world}")
    private String prefix;

    @Resource(name="defaultThreadPool")
    private ExecutorService threadPool;

    @Override
    public String sayHelloWorld(String name) {
        String result;
        try {
            result = threadPool.submit(new ConcatStringTask(prefix, name)).get();
        } catch (Exception e) {
            result = name;
        }
        return result;
    }
}

class ConcatStringTask implements Callable<String> {
    private String prefix;
    private String name;

    ConcatStringTask(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        return this.prefix + this.name;
    }
}
