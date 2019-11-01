package cn.thislx.security.dao;

import cn.thislx.security.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户角色关系Mapper
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/10/20 17:26
 **/
@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);
}
