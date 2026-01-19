package com.beyond.basic.board.author.domain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @ToString
public class Author {
    private Long id;
    private String name;
    private String email;
    private String password;
}
