package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Table(name = "da_order_detail")
@Setter
@Getter
public class DaOrderDetail {
    /**
     * 订单明细id
     */
    @Id
    @Column(name = "order_detail_id")
    @ApiModelProperty(value = "订单明细id")
    private String orderDetailId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    @ApiModelProperty(value = "订单id")
    private String orderId;

    /**
     * 驾校id
     */
    @Column(name = "driver_school_id")
    @ApiModelProperty(value = "驾校id")
    private String driverSchoolId;

    /**
     * 投保人员id
     */
    @Column(name = "insure_id")
    @ApiModelProperty(value = "投保人员id")
    private String insureId;

    /**
     * 投保状态 0 未成功 1 成功
     */
    @ApiModelProperty(value = "投保状态 0 未成功 1 成功")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 投保时间
     */
    @Column(name = "apply_date")
    @ApiModelProperty(value = "投保时间")
    private Date applyDate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "is_deleted")
    private String isDeleted;
    
    /**
     * 序号
     */
    @Column(name = "seq_no")
    private Integer seqNo;
    
    @Column(name = "cont_no")
    private String contNo;
    
    /**
     * 批次号
     */
    private String cusorderid;
    
    @Transient
    private String name;
    
    @Transient
    private String sex;
    
    @Transient
    private String idcard;
}