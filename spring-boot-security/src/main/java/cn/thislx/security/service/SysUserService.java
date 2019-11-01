package cn.thislx.security.service;

import cn.thislx.security.dao.SysUserMapper;
import cn.thislx.security.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/10/20 17:26
 **/
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public SysUser selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public SysUser selectByName(String name) {
        return userMapper.selectByName(name);
    }
}

