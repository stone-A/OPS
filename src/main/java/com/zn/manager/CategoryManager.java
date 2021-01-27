package com.zn.manager;


import com.zn.entity.CategoryDO;
import com.zn.mapper.CategoryMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zn on 2015/9/23.
 */
@Service
@Cacheable
public class CategoryManager {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * c查询类目
     *
     * @return
     */
    @Cacheable(value = "guava", key = "'zn:cat_list'")
    public List<CategoryDO> listAll() {
        return categoryMapper.selectAll();
    }



    public int delCategoryNode(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询类目树
     * :Integer name 类目名称; String parentId 父节点id;
     */
    public List<CategoryDO> queryByNameAndParentId(String name, Integer parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("parent_id", parentId);
        return categoryMapper.selectCategory(map);
    }

    public Integer selectSpuCountByCategoryId(Integer categoryId) {
        return categoryMapper.selectSpuCountByCategoryId(categoryId);
    }

    /**
     * 查询类目id 所有父节点
     * param Integer
     */
    public List<CategoryDO> selectParentNodeList(Integer id) {
        List<CategoryDO> categoryList = categoryMapper.selectAll();
        List<CategoryDO> resultList = new ArrayList<>();
        for (CategoryDO entityDo : categoryList) {
            if (entityDo.getId().equals(id)) {
                if (entityDo.getParentId() == 0) {
                    resultList.add(entityDo);
                    break;
                } else {
                    resultList.add(entityDo);
                    resultList = getParentNode(categoryList, entityDo, resultList);
                }
            }
        }
        return resultList;
    }

    private List<CategoryDO> getParentNode(List<CategoryDO> list, CategoryDO node, List<CategoryDO> resultList) {
        for (CategoryDO entityDo : list) {
            if (entityDo.getId().equals(node.getParentId())) {
                resultList.add(entityDo);
                return getParentNode(list, entityDo, resultList);
            }
        }
        return resultList;
    }

    /**
     * 查询是否有子节点
     * param Integer
     */
    public Boolean categoryChildExist(Integer id) {
        return categoryMapper.categoryChildExist(id) != null;
    }


    public CategoryDO getById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据类目id集合查询符合条件的类目信息
     */
    public List<CategoryDO> queryByIds(Collection<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return categoryMapper.selectByIds(ids);
    }

    /**
     * 返回指定类目 id 的一级类目
     */
    public CategoryDO getRoot(Integer categoryId) {
        CategoryDO categoryDO = getById(categoryId);
        if (categoryDO == null) {
            return null;
        }
        Integer parentId = categoryDO.getParentId();

        // 到达根节点，判断是否是手机类目
        if (parentId == 0) {
            return categoryDO;
        } else {
            return getRoot(parentId);
        }
    }

    /**
     * 返回指定类目的一级类目
     */
    public CategoryDO getRoot(CategoryDO categoryDO) {
        Integer parentId = categoryDO.getParentId();
        if (parentId == 0) {
            return categoryDO;
        }
        return getRoot(parentId);
    }
}
