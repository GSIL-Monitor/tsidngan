package cn.dingan.tsdingan.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TouristInfoDTO {
    private String InsurRegion;//    保险地域类型  string(10)      非空  取值：A-境内；B-境外
    private String TravelAgencyName;//   旅行社名称   string(120)     非空  
    private String TouristGroupNo;// 旅游团编码   string(20)      可空  
    private String TouristContNo;//  旅游合同编码  string(20)      可空  
    private String TouristTrack;//   旅游路线    string(2000)        非空  多个旅游目的地以“|”分割
    private String RiskDisclosure;// 风险告知    string(1)       非空  取值：0-否；1-是
    private String TouristStartDate;//   行程开始日期  string(20)      非空  格式：YYYY-MM-DD
    private String StartTime;//  行程开始时间  string(20)      非空  格式：HH:mm:ss
    private String TouristEndDate;// 行程结束日期  string(20)      非空  格式：YYYY-MM-DD
    private String EndTime;//    行程结束时间  string(20)      非空  格式：HH:mm:ss

}
