package cc.yuerblog.demo.models;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(name="user_id")
    private long id;
    @JSONField(name="user_name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
