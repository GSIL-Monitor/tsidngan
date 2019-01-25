package cn.dingan.tsdingan.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class InsuredList {
    
    private List<InsuredDTO> InsuredDTO;

    public List<InsuredDTO> getInsuredDTO() {
        return InsuredDTO;
    }
    @XmlElement(name = "InsuredDTO")
    public void setInsuredDTO(List<InsuredDTO> insuredDTO) {
        InsuredDTO = insuredDTO;
    }
}
