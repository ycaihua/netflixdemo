package cn.ibabygroup.auth.repository;

import cn.ibabygroup.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zengxiaobo on 2016/8/4 for netflixdemo
 */
@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
