package cn.thislx.security.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户角色实体
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/10/20 17:23
 **/
@Data
@ToString
public class SysUserRole implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;
}

