package com.nw.repository;

import com.nw.po.ResourceClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ResourceClassRepository extends JpaRepository<ResourceClass, Integer> {
    public ResourceClass findResourceClassById(Long id);
    public ResourceClass findResourceClassByTypename(String typename);
    @Modifying
    @Query(value = "delete from resourceclass  where id in ?1",nativeQuery = true)
    int deleteByIds(long[] ids);


    int deleteById(long id);
}
