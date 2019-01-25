package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Table(name = "meal_detail")
@Setter
@Getter
public class MealDetail {
    @Id
    @Column(name = "detail_id")
    private String detailId;

    @Column(name = "meal_id")
    private String mealId;

    /**
     * 险种名称
     */
    @ApiModelProperty(value = "险种名称")
    private String name;

    /**
     * 责任范围
     */
    @ApiModelProperty(value = "责任范围")
    private String liability;

    /**
     * 保障金额
     */
    @Column(name = "guarantee_amount")
    @ApiModelProperty(value = "保障金额")
    private BigDecimal guaranteeAmount;

    /**
     * 免赔额
     */
    @ApiModelProperty(value = "免赔额")
    private BigDecimal deductible;

    /**
     * 赔付比例
     */
    @Column(name = "compensation_ratio")
    @ApiModelProperty(value = "赔付比例")
    private String compensationRatio;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "is_deleted")
    private String isDeleted;
}