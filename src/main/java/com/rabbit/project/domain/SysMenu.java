package com.rabbit.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;


}
