package com.nw.ServiceImpl;

import com.nw.po.User;
import com.nw.repository.UserRepository;
import com.nw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<User> findAllUser(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    @Override
    public Long getAllCount() {
        return userRepository.count();
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean removeUser(long id) {
        userRepository.deleteById(id);
        return true;
    }
}
