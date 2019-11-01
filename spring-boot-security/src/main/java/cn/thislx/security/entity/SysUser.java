package cn.thislx.security.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户实体
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/10/20 17:22
 **/
@Data
@ToString
public class SysUser implements Serializable {
    static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String password;
}
