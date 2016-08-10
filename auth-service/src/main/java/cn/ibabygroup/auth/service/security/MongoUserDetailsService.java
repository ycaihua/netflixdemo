package cn.ibabygroup.auth.service.security;

import cn.ibabygroup.auth.domain.User;
import cn.ibabygroup.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by zengxiaobo on 2016/8/4 for netflixdemo
 */
@Service
public class MongoUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findOne(username);
        if (null==user){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
