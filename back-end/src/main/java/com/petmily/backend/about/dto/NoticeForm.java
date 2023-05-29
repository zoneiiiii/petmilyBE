package com.petmily.backend.about.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeForm {
	private Long no;
	@NotEmpty(message="제목은 필수항목입니다.") // 해당 값이 Null 또는 빈 문자열("")을 허용하지 않음을 의미.
	@Size(max=255) // 최대 길이가 255바이트를 넘으면 안된다는 의미
	private String subject;
	@NotEmpty(message="내용은 필수항목입니다.")
	private String content;
}
