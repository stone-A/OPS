package com.zn.controller;

import com.zn.manager.CategoryManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhounan on 2016/1/19.
 */
@Controller
@RequestMapping("cat")
public class CatController {

    private final  Logger LOGGER = LoggerFactory.getLogger(CatController.class);

    @Autowired
    private CategoryManager categoryManager;


    /**
     * @Description 输出list页面
     * @author zn
     * @param: SaleCategory
     **/
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String pageList() throws IOException, TemplateException {
        categoryManager.listAll();
        LOGGER.info("listAll");
        return "";
    }


}