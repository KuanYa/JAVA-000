package io.kimmking.jdbc;

import io.kimmking.bean.initialization.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    /**
     * 获取用户集合
     *
     * @return
     * @throws Exception
     */
    List<User> listUser() throws Exception;

    /**
     * 添加人员
     *
     * @throws Exception
     */
    void addUser()throws Exception;

    /**
     * 修改人员
     *
     * @throws Exception
     */
    void modifyUser()throws Exception;

    /**
     * 删除人员
     *
     * @throws Exception
     */
    void deleteUser() throws Exception;

}
