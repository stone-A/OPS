package com.zn.entity;

import lombok.Data;

import java.util.Date;

/**
 * DO基础属性
 *
 * @author youmoo
 * @since 15/10/8 21:01
 */
@Data
public class BaseDO{

    /**
     * 创建人id
     */
    protected String creatorId;

    /**
     * 创建人名称
     */
    protected String creatorName;

    /**
     * 创建时间
     */
    protected Date gmtCreate;

    /**
     * 修改人id
     */
    protected String modifierId;

    /**
     * 修改人名称
     */
    protected String modifierName;

    /**
     * 修改时间
     */
    protected Date gmtModified;

}
