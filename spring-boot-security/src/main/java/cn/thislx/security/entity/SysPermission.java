package cn.thislx.security.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 用户权限表
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/10/30 14:50
 **/
@Data
@ToString
public class SysPermission implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String url;

    private Integer roleId;

    private String permission;

    private List<String> permissions;


    public void setPermission(String permission) {
        this.permission = permission;
        this.permissions = Arrays.asList(permission.split(","));
    }
}
