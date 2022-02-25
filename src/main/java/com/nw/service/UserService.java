package com.nw.service;

import com.nw.po.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User getUser(String account);
    User register(User user);
    public List<User> findAllUser(Pageable pageable);
    public Long getAllCount();
    public boolean removeUser(long id);
}
