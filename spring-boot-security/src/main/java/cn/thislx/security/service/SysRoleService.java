package cn.thislx.security.service;

import cn.thislx.security.dao.SysRoleMapper;
import cn.thislx.security.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lixiang
 * @version V1.0
 * @date 2019/10/20 17:27
 **/
@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }


    public SysRole selectByName(String name){
        return roleMapper.selectByName(name);
    }
}

