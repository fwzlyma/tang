package com.nw.repository;

import com.nw.po.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
    /**
     * 获取所有唐卡 + 分页
     * @param pageable
     * @return
     */
    public List<Resource> findAllBy(Pageable pageable);

    /**
     * 根据id查找唐卡
     * @param id
     * @return
     */
    public Resource findResourceById(Long id);

    /**
     * 根据类型查找唐卡
     * @param id
     * @return
     */
    public List<Resource> findResourceByTypeid(Long id, Pageable pageable);

    /**
     * 获取一个类型的数量
     * @param id
     * @return
     */
    public int countResourceByTypeid(Long id);
    @Modifying
    @Query(value = "delete from resource  where id in ?1",nativeQuery = true)
    int deleteByIds(long[] ids);

    @Modifying
    @Query(value = "delete from resource  where id = ?1",nativeQuery = true)
    int deleteById(long id);

    List<Resource> getAllByType(String type, Pageable pageable);

    int countByType(String type);

}
