package com.nw.ServiceImpl;

import com.nw.po.Resource;
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
