package com.nw.service;

import com.nw.po.User;

public interface UserService {

    User getUser(String account);
    User register(User user);
}
