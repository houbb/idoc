package com.github.houbb.idoc.test.service;

import com.github.houbb.idoc.test.model.User;

/**
 * 查询用户服务类
 * @author binbin.hou
 * @since 0.0.1
 */
public interface QueryUserService {

    /**
     * 根据用户信息查询用户
     * @param user 用户信息
     * @return 结果
     * @since 0.0.2,2019/02/12
     */
    public User queryUser(final User user);

}
