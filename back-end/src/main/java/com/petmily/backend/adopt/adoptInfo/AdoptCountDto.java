package com.petmily.backend.adopt.adoptInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptCountDto {
    private Long totalCount;
    private Long waitingCount;
    private Long successCount;
    private Long failCount;

}
