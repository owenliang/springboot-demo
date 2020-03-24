package cc.yuerblog.demo.services.impl;

import cc.yuerblog.demo.daos.demo1.UserMapper;
import cc.yuerblog.demo.models.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

@Service("testService")
public class TestServiceImpl implements cc.yuerblog.demo.services.TestService {
    @Value("${testservice.prefix:hello world}")
    private String prefix;

    @Resource(name="defaultThreadPool")
    private ExecutorService threadPool;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String sayHelloWorld(String name) {
        String result;
        try {
            result = threadPool.submit(new ConcatStringTask(userMapper, prefix, name)).get();
        } catch (Exception e) {
            result = name;
        }
        return result;
    }
}

class ConcatStringTask implements Callable<String> {
    private String prefix;
    private String name;
    private UserMapper mapper;

    ConcatStringTask(UserMapper mapper, String prefix, String name) {
        this.mapper = mapper;
        this.prefix = prefix;
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        User user = new User();
        user.setName(name);

        try {
            mapper.insertUser(user);
            List<User> users = mapper.findUserByName(name);
            Map<String, Object> ret = new HashMap<>();
            ret.put("error_code", 0);
            ret.put("error_msg", "success");
            ret.put("data", users);
            JSONObject json = (JSONObject)JSONObject.toJSON(ret);
            return json.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.prefix + this.name;
    }
}
