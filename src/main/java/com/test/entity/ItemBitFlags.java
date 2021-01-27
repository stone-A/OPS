package com.test.entity;

/**
 * Created by zhounan on 2016/3/1.
 */
public enum ItemBitFlags {

    BIT_FLAG_HUABEI(1,"花呗分期");////2^0

    ItemBitFlags(long value,String name){
        this.value=value;
        this.name=name;
    }

    final long value;

    final String name;

    public long getValue(){return this.value;}

    public String getName(){return this.name;}
}
