package cn.dingan.tsdingan.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class MainContDTOList {
    
    private List<MainContDTO> MainContDTO;

    public List<MainContDTO> getMainContDTO() {
        return MainContDTO;
    }
    @XmlElement(name = "MainContDTO")
    public void setMainContDTO(List<MainContDTO> mainContDTO) {
        MainContDTO = mainContDTO;
    }

}
