package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Setter
@Getter
public class Serialno {
    @Id
    private String id;

    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    private Integer serialno;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "is_deleted")
    private String isDeleted;
}