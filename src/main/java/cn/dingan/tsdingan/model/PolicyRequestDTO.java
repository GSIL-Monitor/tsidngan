package cn.dingan.tsdingan.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "PolicyRequestDTO")
public class PolicyRequestDTO {
    
    private TranslationDTO TranslationDTO;
    
    
    private MainContDTOList MainContDTOList;
    
    
	public TranslationDTO getTranslationDTO() {
		return TranslationDTO;
	}
	@XmlElement(name = "TranslationDTO")
	public void setTranslationDTO(TranslationDTO translationDTO) {
		TranslationDTO = translationDTO;
	}

	public MainContDTOList getMainContDTOList() {
		return MainContDTOList;
	}
	@XmlElement(name = "MainContDTOList")
	public void setMainContDTOList(MainContDTOList mainContDTOList) {
		MainContDTOList = mainContDTOList;
	}
    
}
