package com.thoughtworks.lirenlab.login;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("loginfailure")
    public ModelAndView fail() {
        Map<String, String> model = Maps.newHashMap();
        model.put("error", "用户名或密码错误，请重新输入！");
        return new ModelAndView("login.jsp", model);
    }
}
