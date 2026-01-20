package com.beyond.basic.board.author.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorCreateDto {
    @NotBlank(message = "이름이 없습니다.")
    private String name;
    @NotBlank(message = "이메일이 없습니다.")
    private String email;
    @NotBlank(message = "password가 없습니다.")
    @Size(min=8,message = "password가 짧습니다.")
    private String password;
}
