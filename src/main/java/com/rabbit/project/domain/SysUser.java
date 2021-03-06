package com.rabbit.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态 0：禁用   1：正常
     */
    private Integer status;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 角色列表
     */
    @TableField(exist = false)
    List<String> roleList;
}
