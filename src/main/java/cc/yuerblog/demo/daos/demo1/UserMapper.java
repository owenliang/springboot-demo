package cc.yuerblog.demo.daos.demo1;

import cc.yuerblog.demo.models.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    long insertUser(User user);
    List<User> findUserByName(String name);
}
