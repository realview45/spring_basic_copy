package com.beyond.basic.b1_basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MImage {
    private String name;
    private String email;
    private MultipartFile profileImage;
}
