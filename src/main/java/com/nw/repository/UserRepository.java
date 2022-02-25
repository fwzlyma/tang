package com.nw.repository;

import com.nw.po.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByAccount(String account);
    List<User> findAllBy(Pageable pageable);
    @Modifying
    @Query(value = "update user set password=?1  where account =?2",nativeQuery = true)
    int updateP(String password,String account);
    int deleteById(Long id);
    @Modifying
    @Query(value = "update user set type ='1'  where id =?1",nativeQuery = true)
    int updateS(Long id);
    @Modifying
    @Query(value = "update user set type ='2'  where id =?1",nativeQuery = true)
    int updateO(Long id);
}
