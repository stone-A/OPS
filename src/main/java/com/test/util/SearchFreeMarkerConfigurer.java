package com.test.util;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Chen Hui
 * @since 2016/2/2
 */
public class SearchFreeMarkerConfigurer extends FreeMarkerConfigurer {
    private boolean loadCommonTemplate = true;
    public SearchFreeMarkerConfigurer(boolean loadCommonTemplate) {
        this.loadCommonTemplate = loadCommonTemplate;
    }
    public void setPreTemplatePath(String commonTemplatePath) throws IOException {
        if(loadCommonTemplate == true) {
            FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File(commonTemplatePath));
            this.setPreTemplateLoaders(fileTemplateLoader);
        }
    }
}
