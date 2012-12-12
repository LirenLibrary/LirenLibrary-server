package com.thoughtworks.lirenlab.interfaces.about.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: zhengli
 * Date: 11/30/12
 */
@Controller
@RequestMapping(value = "/pages")
public class PagesController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "pages/about";
    }
}
