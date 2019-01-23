package cn.dingan.tsdingan.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@XmlRootElement(name = "PolicyRequestDTO")
public class PolicyRequestDTO {
    @XmlElement(name = "TranslationDTO")
    private TranslationDTO TranslationDTO;
    @XmlElement(name = "MainContDTO")
    private MainContDTO MainContDTO;
    @XmlElement(name = "RemittGrpDTO")
    private RemittGrpDTO RemittGrpDTO;
}
