package com.zn.until;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ZN
 * 2015-12-7 上午10:16:04
 * TODO spring框架外获取框架内内容
 */
@Component
public final class SpringContextUtil implements ApplicationContextAware {

  private static ApplicationContext ctx;

  /**
   *
   * @param id spring bean ID值
   * @return spring bean对象
   */
  public static Object getBean(String id) {
    if (ctx == null) {
      throw new NullPointerException("ApplicationContext is null");
    }
    return ctx.getBean(id);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationcontext)
          throws BeansException {
    ctx = applicationcontext;
  }
}