package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Table(name = "insurance_meal")
@Setter
@Getter
public class InsuranceMeal {
    @Id
    @Column(name = "meal_id")
    private String mealId;

    /**
     * 保费
     */
    @Column(name = "Prem")
    @ApiModelProperty(value = "保费")
    private BigDecimal prem;

    /**
     * 每人累计最高赔偿限额
     */
    @Column(name = "max_compensation")
    @ApiModelProperty(value = "每人累计最高赔偿限额")
    private BigDecimal maxCompensation;

    /**
     * 每人意外身故/残疾最高赔偿金
     */
    @Column(name = "max_accident_compensation")
    @ApiModelProperty(value = "每人意外身故/残疾最高赔偿金")
    private BigDecimal maxAccidentCompensation;

    /**
     * 每人意外医疗费用最高赔偿金
     */
    @Column(name = "max_medical_compensation")
    @ApiModelProperty(value = "每人意外医疗费用最高赔偿金")
    private BigDecimal maxMedicalCompensation;

    /**
     * 套餐编码
     */
    @Column(name = "ContplanCode")
    @ApiModelProperty(value = "套餐编码")
    private String contplancode;

    /**
     * 保额
     */
    @Column(name = "MainAmount")
    @ApiModelProperty(value = "保额")
    private BigDecimal mainamount;

    /**
     * 附加保险额
     */
    @Column(name = "AdditionalAmount")
    @ApiModelProperty(value = "附加保险额")
    private BigDecimal additionalamount;

    /**
     * 保险期 以天为单位
     */
    @Column(name = "insurance_period")
    @ApiModelProperty(value = "保险期 以天为单位")
    private Integer insurancePeriod;

    /**
     * 套餐类型(1学员投保,2教练员投保 3 特殊人员投保)
     */
    @Column(name = "meal_type")
    @ApiModelProperty(value = "套餐类型(1学员投保,2教练员投保 3 特殊人员投保)")
    private String mealType;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "is_deleted")
    private String isDeleted;
}