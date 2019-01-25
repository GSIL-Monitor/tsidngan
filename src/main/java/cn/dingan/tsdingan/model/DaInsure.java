package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Table(name = "da_insure")
@Setter
@Getter
public class DaInsure {
    /**
     * id
     */
    @Id
    @Column(name = "insure_id")
    @ApiModelProperty(value = "id")
    private String insureId;

    /**
     * 驾培id
     */
    @Column(name = "driver_school_id")
    @ApiModelProperty(value = "驾培id")
    private String driverSchoolId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @Column(name = "id_number")
    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 性别(1男 2 女)
     */
    @ApiModelProperty(value = "性别(1男 2 女)")
    private String sex;

    /**
     * 出生日期
     */
    @Column(name = "birth_date")
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    /**
     * 投保人员类型(1 学员投保 2教练员投保 3特殊人员投保)
     */
    @ApiModelProperty(value = "投保人员类型(1 学员投保 2教练员投保 3特殊人员投保)")
    private String type;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "insure_date")
    private Date insureDate;

    @Column(name = "create_date")
    private Date createDate;
}