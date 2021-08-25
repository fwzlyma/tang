package com.nw.service;

import com.nw.po.Resource;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResourceService {
    /**
     * 获取所有资源
     * @param pageable
     * @return
     */
    public List<Resource> findAllResource(Pageable pageable);

    /**
     * 获取个数
     * @return
     */
    public Long getAllCount();

    /**
     * 根据id获取唐卡
     * @param id
     * @return
     */
    public Resource findResourceById(Long id);

    /**
     * 获取某一类型下的所有唐卡
     * @param id
     * @return
     */
    public List<Resource> findResourceByTypeid(Long id, Pageable pageable);

    /**
     * 获取类型数量
     * @param id
     * @return
     */
    public int findCountByTypeId(Long id);
    /**
     * 添加唐卡
     * @param resource
     * @return
     */
    Resource addResource(Resource resource);


    /**
     * 删除唐卡
     * @param id
     * @return
     */
    public boolean removeResource(long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int removeAnyResources(long[] ids);


    /**
     * 更新唐卡
     * @param resource
     * @return
     */
    Resource updateResource(Resource resource);



    /**
     * 更新图片
     * @param map
     * @return
     */
//    public boolean updateResourcePic(Map<String, Object> map);

}
