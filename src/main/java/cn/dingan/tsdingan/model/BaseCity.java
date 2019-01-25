package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import javax.persistence.*;
import lombok.*;

@Table(name = "base_city")
@Setter
@Getter
public class BaseCity {
    /**
     * ID
     */
    @Id
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String name;

    /**
     * 父ID
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value = "父ID")
    private String parentId;

    /**
     * 简称
     */
    @ApiModelProperty(value = "简称")
    private String simple;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer level;

    /**
     * 电话区号
     */
    @ApiModelProperty(value = "电话区号")
    private String code;

    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String zip;

    /**
     * 全称
     */
    @ApiModelProperty(value = "全称")
    private String mergername;

    /**
     * 拼音
     */
    @ApiModelProperty(value = "拼音")
    private String pinyin;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String ing;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String lat;

    /**
     * 删除标识
     */
    @Column(name = "is_deleted")
    @ApiModelProperty(value = "删除标识")
    private String isDeleted;
}