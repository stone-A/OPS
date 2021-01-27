package com.zn.mapper;


import com.zn.common.RedisConstant;
import com.zn.entity.CategoryDO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface CategoryMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(CategoryDO record);

    CategoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CategoryDO record);

    int updateByPrimaryKey(CategoryDO record);

    List<CategoryDO> selectCategory(Map<String, Object> dataMap);

    List<CategoryDO> selectAll();

    int selectSpuCountByCategoryId(Integer categoryId);

    /**
     * 查询类目是否有子节点
     * param Integer
     */
    Boolean categoryChildExist(Integer id);

    List<CategoryDO> selectByIds(Collection<Integer> ids);
}