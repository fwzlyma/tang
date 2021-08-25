package com.nw.ServiceImpl;

import com.nw.po.User;
import com.nw.repository.UserRepository;
import com.nw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String account) {
        return userRepository.findUserByAccount(account);
    }

    @Override
    public User register(User user) {
        User addUser = userRepository.save(user);
        return addUser;
    }
}
