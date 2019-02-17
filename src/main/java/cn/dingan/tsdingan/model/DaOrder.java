package cn.dingan.tsdingan.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Table(name = "da_order")
@Setter
@Getter
public class DaOrder {
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 驾校下单单号
     */
    @ApiModelProperty(value = "驾校下单单号")
    private String cusorderid;

    /**
     * 驾校id
     */
    @Column(name = "driver_school_id")
    @ApiModelProperty(value = "驾校id")
    private String driverSchoolId;

    /**
     * 状态 0 未完成支付 1 完成支付
     */
    @ApiModelProperty(value = "状态 0 未完成支付 1 完成支付")
    private String status;

    /**
     * 鼎安保单号
     */
    @Column(name = "cont_no")
    @ApiModelProperty(value = "鼎安保单号")
    private String contNo;

    /**
     * 订单金额
     */
    @Column(name = "order_amount")
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    /**
     * 通联收银宝交易流水号
     */
    @ApiModelProperty(value = "通联收银宝交易流水号")
    private String trxid;

    private String remark;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "is_deleted")
    private String isDeleted;
    
    @Transient
    private List<DaOrderDetail> orderDetailList;
}