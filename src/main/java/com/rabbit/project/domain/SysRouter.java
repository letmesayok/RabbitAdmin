package com.rabbit.project.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangql
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRouter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父级id
     */
    private Integer pid;

    private String name;

    private String url;

    private String sort;

    private String type;


}
