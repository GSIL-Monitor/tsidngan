package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Table(name = "account_numer")
@Setter
@Getter
public class AccountNumer {
    private String id;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer code;

    @Column(name = "create_date")
    private Date createDate;
}