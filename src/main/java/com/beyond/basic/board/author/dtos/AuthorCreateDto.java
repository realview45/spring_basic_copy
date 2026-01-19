package com.beyond.basic.board.author.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorCreateDto {
    private String name;
    private String email;
    private String password;
}
