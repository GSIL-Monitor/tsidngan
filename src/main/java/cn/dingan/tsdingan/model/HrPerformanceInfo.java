package cn.dingan.tsdingan.model;

import io.swagger.annotations.*;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Table(name = "hr_performance_info")
@Setter
@Getter
public class HrPerformanceInfo {
    /**
     * 绩效信息ID
     */
    @Id
    @Column(name = "performance_info_id")
    @ApiModelProperty(value = "绩效信息ID")
    private String performanceInfoId;

    /**
     * 导入日期
     */
    @Column(name = "import_date")
    @ApiModelProperty(value = "导入日期")
    private String importDate;

    /**
     * 员工号
     */
    @Column(name = "employee_code")
    @ApiModelProperty(value = "员工号")
    private String employeeCode;

    /**
     * 员工姓名
     */
    @Column(name = "employee_name")
    @ApiModelProperty(value = "员工姓名")
    private String employeeName;

    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Integer score;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private String grade;

    /**
     * 状态0:为处理，1:已处理
     */
    @ApiModelProperty(value = "状态0:为处理，1:已处理")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    @ApiModelProperty(value = "创建人")
    private String createUser;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    @ApiModelProperty(value = "修改时间")
    private Date updateDate;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    @ApiModelProperty(value = "修改人")
    private String updateUser;

    /**
     * 删除标识
     */
    @Column(name = "is_deleted")
    @ApiModelProperty(value = "删除标识")
    private String isDeleted;

    /**
     * 所属公司
     */
    @Column(name = "company_id")
    @ApiModelProperty(value = "所属公司")
    private String companyId;

    /**
     * 所属部门
     */
    @Column(name = "org_id")
    @ApiModelProperty(value = "所属部门")
    private String orgId;

    /**
     * 所属公司名称
     */
    @Column(name = "company_name")
    @ApiModelProperty(value = "所属公司名称")
    private String companyName;

    /**
     * 所属部门名称
     */
    @Column(name = "org_name")
    @ApiModelProperty(value = "所属部门名称")
    private String orgName;

    /**
     * 部门代码
     */
    @Column(name = "org_code")
    @ApiModelProperty(value = "部门代码")
    private String orgCode;
}