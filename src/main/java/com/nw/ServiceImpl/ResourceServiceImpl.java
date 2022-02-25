package com.nw.ServiceImpl;

import com.nw.po.Resource;
import com.nw.po.ResourceClass;
import com.nw.repository.ResourceClassRepository;
import com.nw.repository.ResourceRepository;
import com.nw.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ResourceClassRepository resourceClassRepository;
    @Override
    public List<Resource> findAllResource(Pageable pageable) {
        return resourceRepository.findAllBy(pageable);
    }

    @Override
    public Long getAllCount() {
        return resourceRepository.count();
    }

    @Override
    public Resource findResourceById(Long id) {
        return resourceRepository.findResourceById(id);
    }

    @Override
    public List<Resource> findResourceByTypeid(Long id, Pageable pageable) {
        return resourceRepository.findResourceByTypeid(id, pageable);
    }

    @Override
    public int findCountByTypeId(Long id) {
        return resourceRepository.countResourceByTypeid(id);
    }

    @Override
    public List<Resource> findResourceByTypeName(String typename, Pageable pageable) {
        //根据类型名 获取到类型
        ResourceClass resourceClass = resourceClassRepository.findResourceClassByTypename(typename);
        Long typeId = resourceClass == null?0L:resourceClass.getId();

        //根据类型ID获取到唐卡列表
        List<Resource> list = resourceRepository.findResourceByTypeid(typeId, pageable);
        return list;
    }

    @Override
    public Resource addResource(Resource resource) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resource.setUploadtime(sdf.format(new Date()));
        return resourceRepository.save(resource);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean removeResource(long id) {
        resourceRepository.deleteById(id);
        return true;
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int removeAnyResources(long[] ids) {
        return resourceRepository.deleteByIds(ids);
    }


    @Override
    public Resource updateResource(Resource resource) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resource.setUploadtime(sdf.format(new Date()));
        return resourceRepository.save(resource);
    }

//    /**
//     * 通过类型查找唐卡
//     * @return
//     */
//    @Override
//    public List<Resource> selectResource(String type) {
//        return (List<Resource>) resourceRepository.findAllByType(type);
//    }


//    @Override
//    public boolean updateResourcePic(Map<String, Object> map) {
//        return resourceRepository.updateResourcePic();
//    }


}
