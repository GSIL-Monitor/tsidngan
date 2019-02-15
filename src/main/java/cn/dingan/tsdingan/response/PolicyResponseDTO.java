package cn.dingan.tsdingan.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyResponseDTO {
    
    private ResponsePolicyRequestDTO PolicyRequestDTO;
    
    private List<ResopnseMainContDTO> MainContDTOList;
}
