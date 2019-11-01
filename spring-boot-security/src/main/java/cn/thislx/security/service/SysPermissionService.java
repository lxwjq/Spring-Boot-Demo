package cn.thislx.security.service;

import cn.thislx.security.dao.SysPermissionMapper;
import cn.thislx.security.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限Service
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/10/30 14:52
 **/
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper permissionMapper;

    /**
     * 获取指定角色所有权限
     */
    public List<SysPermission> listByRoleId(Integer roleId) {
        return permissionMapper.listByRoleId(roleId);
    }
}