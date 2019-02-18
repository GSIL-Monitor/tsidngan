package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Table(name = "driver_school")
@Setter
@Getter
public class DriverSchool {
    /**
     * id
     */
    @Id
    @Column(name = "driver_school_id")
    @ApiModelProperty(value = "id")
    private String driverSchoolId;

    /**
     * 驾校机构名称
     */
    @ApiModelProperty(value = "驾校机构名称")
    private String name;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域")
    private String area;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;

    /**
     * 经办人手机号
     */
    @ApiModelProperty(value = "经办人手机号")
    private String phone;

    /**
     * 营业执照统一社会信用代码
     */
    @Column(name = "unified_social_credit_code")
    @ApiModelProperty(value = "营业执照统一社会信用代码")
    private String unifiedSocialCreditCode;

    /**
     * 驾培机构邮箱
     */
    @ApiModelProperty(value = "驾培机构邮箱")
    private String email;

    /**
     * 营业执照地址
     */
    @Column(name = "license_url")
    @ApiModelProperty(value = "营业执照地址")
    private String licenseUrl;

    /**
     * 是否已审核(1未审核 2 已审核)
     */
    @Column(name = "is_examine")
    @ApiModelProperty(value = "是否已审核(1未审核 2 已审核)")
    private String isExamine;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String account;
    
    @Column(name = "is_deleted")
    private String isDeleted;
    
    @Column(name = "account_type")
    private String accountType;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;
    
    @Transient
    private String token;
}