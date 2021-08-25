package com.nw.service;

import com.nw.po.ResourceClass;

import java.util.List;

public interface ResourceClassService {
    /**
     * 获取所有唐卡类型
     * @return
     */
    public List<ResourceClass> getAllType();

    /**
     * 根据id获取
     * @param id
     * @return
     */
    public ResourceClass findResourceClassById(Long id);

    /**
     * 根据类型名获取
     * @param typeName
     * @return
     */
    public ResourceClass findResourceClassByType(String typeName);

    /**
     * 获取个数
     * @return
     */
    public Long getAllCount();
    /**
     * 添加唐卡类型
     * @param resourceClass
     * @return
     */
    public ResourceClass addResourceClass(ResourceClass resourceClass);

    /**
     * 批量删除类型
     * @param ids
     * @return
     */
    public int removeAnyClass(long[] ids);

    /**
     * 删除唐卡类型
     * @param id
     * @return
     */
    public int removeResourceClass(long id);

    /**
     * 更新类型信息
     * @param resourceClass
     * @return
     */
    public ResourceClass updateResourceClass(ResourceClass resourceClass);
}
