package com.test.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhounan on 2016/3/4.
 */
public class myTest {

    private final static Logger logger = LoggerFactory.getLogger(myTest.class);

    public static void main(String[] args){

        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(2);
        HashSet<Integer> set=new HashSet<>(list);
        Iterator<Integer> it = set.iterator();
        while (it.hasNext())
        {
          list.add(it.next());
        }



//        ItemReleaseParam param=new ItemReleaseParam();
//        Long result=param.getBitFlag();
//        System.out.println(itemDO.getBitFlag() + "-------------");
//        System.out.println("2 & 3="+(2 & 3)+"-------------");
//        System.out.println("2 | 4="+(2 | 4|8)+"-------------");
        logger.trace("do trace");
        logger.debug("do debug");
        logger.info("do info");
        logger.warn("do warn");
        logger.error("do error");
    }


}
