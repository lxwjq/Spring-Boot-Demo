package cn.thislx.security.service;

import cn.thislx.security.dao.SysUserRoleMapper;
import cn.thislx.security.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lixiang
 * @version V1.0
 * @date 2019/10/20 17:27
 **/
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public List<SysUserRole> listByUserId(Integer userId) {
        return userRoleMapper.listByUserId(userId);
    }
}

