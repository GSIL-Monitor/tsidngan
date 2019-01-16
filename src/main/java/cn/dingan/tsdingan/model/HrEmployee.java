package cn.dingan.tsdingan.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.fasterxml.jackson.annotation.JsonFormat;

import groovy.transform.ToString;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Table(name = "hr_employee")
@Setter
@Getter
@ToString
public class HrEmployee {
    /**
     * employee_id
     */
    @Id
    @Column(name = "employee_id")
    @ApiModelProperty(value = "employee_id")
    private String employeeId;

    /**
     * 工号
     */
    @Excel(name = "工号")
    @ApiModelProperty(value = "工号")
    private String code;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别(1:男,2:女)")
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 照片地址
     */
    @Column(name = "pic_url")
    @ApiModelProperty(value = "照片地址")
    private String picUrl;

    /**
     * 入职时间
     */
    @Excel(name = "入职时间")
    @Column(name = "entry_date")
    @ApiModelProperty(value = "入职时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date entryDate;

    /**
     * 在职时长
     */
    @Column(name = "job_time")
    @ApiModelProperty(value = "在职时长")
    private String jobTime;

    /**
     * 部门id
     */
    @Column(name = "organization_id")
    @ApiModelProperty(value = "部门id")
    private String organizationId;
    
    @Transient
    @ApiModelProperty(value = "部门名称")
    private String organizationName;
    
    /**
     * 部门CODE
     */
    @Transient
    @ApiModelProperty(value = "部门CODE")
    private String organizationCode;

    /**
     * 岗位ID
     */
    @Column(name = "post_id")
    @ApiModelProperty(value = "岗位ID")
    private String postId;
    
    @Transient
    @ApiModelProperty(value = "岗位名称")
    private String postName;
    
    /**
     * 标签(多标签逗号分隔)
     */
    @ApiModelProperty(value = "标签(多标签逗号分隔)")
    private String tag;

    /**
     * 状态(1在职;2:离职)
     */
    @ApiModelProperty(value = "状态(1在职;2:离职)")
    private String status;

    /**
     * 删除标识
     */
    @Column(name = "is_deleted")
    @ApiModelProperty(value = "删除标识")
    private String isDeleted;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateDate;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    @ApiModelProperty(value = "修改人")
    private String updateUser;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    @Column(name = "id_number")
    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    /**
     * 出生日期
     */
    @Excel(name = "出生日期")
    @Column(name = "birth_date")
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthDate;

    /**
     * 生日类型(1:公历;2:农历)
     */
    @Excel(name = "生日类型(1:公历;2:农历)")
    @Column(name = "birth_type")
    @ApiModelProperty(value = "生日类型(1:公历;2:农历)")
    private Integer birthType;

    /**
     * 年龄
     */
    @Excel(name = "年龄")
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 学历
     */
    @Excel(name = "学历(1:高中,2:中专,3:大专,4:本科,5:硕士,6:研究生,7:博士)")
    @ApiModelProperty(value = "学历")
    private String education;

    /**
     * 毕业院校
     */
    @Excel(name = "毕业院校")
    @Column(name = "graduated_school")
    @ApiModelProperty(value = "毕业院校")
    private String graduatedSchool;

    /**
     * 毕业时间
     */
    @Excel(name = "毕业时间")
    @Column(name = "graduated_date")
    @ApiModelProperty(value = "毕业时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date graduatedDate;

    /**
     * 技术职称
     */
    @ApiModelProperty(value = "专业")
    private String technical;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族")
    private String nation;

    /**
     * 籍贯
     */
    @Excel(name = "籍贯")
    @Column(name = "place_origin")
    @ApiModelProperty(value = "籍贯")
    private String placeOrigin;

    /**
     * 婚姻状况(1: 未婚 2 已婚 3 离异)
     */
    @Column(name = "marital_status")
    @ApiModelProperty(value = "婚姻状况(1: 未婚 2 已婚 3 离异)")
    private String maritalStatus;

    /**
     * 政治面貌
     */
    @Excel(name = "政治面貌(1:党员,2:无党派人士,3:预备党员,4:团员,5:群众,6:其他)")
    @ApiModelProperty(value = "政治面貌")
    private String political;

    /**
     * 现居住地址
     */
    @ApiModelProperty(value = "现居住地址")
    private String address;
    
    /**
     * 身份证地址
     */
    @ApiModelProperty(value = "身份证地址")
    @Column(name = "card_address")
    private String cardAddress;

    /**
     * 紧急联系人
     */
    @Excel(name = "紧急联系人")
    @Column(name = "emergency_contact")
    @ApiModelProperty(value = "紧急联系人")
    private String emergencyContact;

    /**
     * 紧急联系电话
     */
    @Excel(name = "紧急联系电话")
    @Column(name = "emergency_tel")
    @ApiModelProperty(value = "紧急联系电话")
    private String emergencyTel;

    /**
     * 个人爱好
     */
    @ApiModelProperty(value = "个人爱好")
    private String hobby;

    /**
     * 职务级别
     */
    @Column(name = "post_level")
    @ApiModelProperty(value = "职务级别")
    private Integer postLevel;

    /**
     * 是否重复入职(1是;2否)
     */
    @Excel(name = "是否重复入职(1是;2否)")
    @Column(name = "is_duplicate_entry")
    @ApiModelProperty(value = "是否重复入职(1是;2否)")
    private Integer isDuplicateEntry;

    /**
     * 转正日期
     */
    @Column(name = "regular_date")
    @ApiModelProperty(value = "转正日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date regularDate;

    /**
     * 离职日期
     */
    @Column(name = "leave_date")
    @ApiModelProperty(value = "离职日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date leaveDate;
    
    @Transient
    @ApiModelProperty(value = "离职开始日期")
    private String leaveDateStart;
    
    @Transient
    @ApiModelProperty(value = "离职截止日期")
    private String leaveDateEnd;

    /**
     * 离职类型(1:辞职;2辞退;2:自动离职)
     */
    @Column(name = "leave_type")
    @ApiModelProperty(value = "离职类型(1:辞职;2辞退;2:自动离职)")
    private Integer leaveType;

    @Column(name = "butt_people")
    @ApiModelProperty(value = "离职对接人")
    private String buttPeople;
    /**
     * 离职原因
     */
    @Column(name = "leave_reason")
    @ApiModelProperty(value = "离职原因")
    private String leaveReason;

    /**
     * 试用期薪资
     */
    @Excel(name = "试用期薪资")
    @Column(name = "probation_salary")
    @ApiModelProperty(value = "试用期薪资")
    private BigDecimal probationSalary;

    /**
     * 转正薪资
     */
    @Column(name = "regular_salary")
    @ApiModelProperty(value = "转正薪资")
    private BigDecimal regularSalary;

    /**
     * 社保购买日期
     */
    @Column(name = "buy_social_date")
    @ApiModelProperty(value = "社保购买日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date buySocialDate;
    
    
    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;
    
    /**
     * 微信用户ID
     */
    @Column(name = "wx_user_id")
    @ApiModelProperty(value = "企业微信用户ID")
    private String wxUserId;

    /**
     * 公积金购买日期
     */
    @Column(name = "buy_provident_date")
    @ApiModelProperty(value = "公积金购买日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date buyProvidentDate;

    /**
     * 福利薪资说明
     */
    @Column(name = "salary_remark")
    @ApiModelProperty(value = "福利薪资说明")
    private String salaryRemark;
    
    @Column(name = "company_code")
    @ApiModelProperty(value = "所属公司编码")
    private String companyCode;
    
    @Column(name = "company_id")
    @ApiModelProperty(value = "所属公司id")
    private String companyId;
    
    @Column(name = "company_name")
    @ApiModelProperty(value = "所属公司名称")
    private String companyName;
    
   
     
}