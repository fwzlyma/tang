package com.nw.ServiceImpl;

import com.nw.po.ResourceClass;
import com.nw.repository.ResourceClassRepository;
import com.nw.service.ResourceClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceClassServiceImpl implements ResourceClassService {
    @Autowired
    ResourceClassRepository resourceClassRepository;

    @Override
    public List<ResourceClass> getAllType() {
        return resourceClassRepository.findAll();
    }

    @Override
    public ResourceClass findResourceClassById(Long id) {
        return resourceClassRepository.findResourceClassById(id);
    }

    @Override
    public ResourceClass findResourceClassByType(String typeName) {
        return resourceClassRepository.findResourceClassByTypename(typeName);
    }

    @Override
    public Long getAllCount() {
        return resourceClassRepository.count();
    }
    /**
     * 新增类型
     * @param resourceClass
     * @return
     */
    @Override
    public ResourceClass addResourceClass(ResourceClass resourceClass) {
        return resourceClassRepository.save(resourceClass);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int removeAnyClass(long[] ids) {
        return resourceClassRepository.deleteByIds(ids);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int removeResourceClass(long id) {
        return resourceClassRepository.deleteById(id);
    }

    /**
     * 更新类型信息
     * @param resourceClass
     * @return
     */
    @Override
    public ResourceClass updateResourceClass(ResourceClass resourceClass) {
        return resourceClassRepository.save(resourceClass);
    }
}
