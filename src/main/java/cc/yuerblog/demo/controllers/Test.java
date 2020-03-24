package cc.yuerblog.demo.controllers;

import cc.yuerblog.demo.services.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Controller
@RequestMapping("/test")
public class Test {
    @Resource(name="testService")
    private TestService testService;

    // /test/hello-world?name=owenliang
    @RequestMapping(value="/hello-world", method = {RequestMethod.GET})
    public void helloWorld(HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false, name = "who") String name) throws IOException {
        response.getWriter().println(testService.sayHelloWorld(name));
    }
}
