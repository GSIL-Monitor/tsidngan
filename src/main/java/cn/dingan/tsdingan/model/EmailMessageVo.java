package cn.dingan.tsdingan.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessageVo {

	private String sendTo;
	private String cc;
	private String title;
	private String content;
	private String[] files;
}
