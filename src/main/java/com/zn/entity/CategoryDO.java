package com.zn.entity;

import lombok.Data;


@Data
public class CategoryDO  extends  BaseDO{
    private Integer id;

    /**
     * 类目名
     */
    private String name;

    /**
     * 父节点id
     */
    private Integer parentId;

    private Integer active;

}