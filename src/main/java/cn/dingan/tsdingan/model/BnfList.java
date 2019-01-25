package cn.dingan.tsdingan.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class BnfList {
    
    
    private List<BnfDTO> BnfDTO;

    public List<BnfDTO> getBnfDTO() {
        return BnfDTO;
    }
    @XmlElement(name = "BnfDTO")
    public void setBnfDTO(List<BnfDTO> bnfDTO) {
        BnfDTO = bnfDTO;
    }
}
