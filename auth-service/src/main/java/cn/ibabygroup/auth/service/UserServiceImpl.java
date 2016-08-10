package cn.ibabygroup.auth.service;

import cn.ibabygroup.auth.domain.User;
import cn.ibabygroup.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by zengxiaobo on 2016/8/4 for netflixdemo
 */
@Service
public class UserServiceImpl implements UserService {
    private final Logger logger= LoggerFactory.getLogger(getClass());
    private static final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;
    @Override
    public void create(User user) {
          User user1=userRepository.findOne(user.getUsername());
        Assert.isNull(user1,"user already exists: "+user.getUsername());
        String hash=encoder.encode(user.getUsername());
        user.setPassword(hash);
        userRepository.save(user);
        logger.info("new user has been created: {}", user.getUsername());
    }
}
