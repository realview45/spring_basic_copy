package com.beyond.basic.board.author.domain;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @ToString
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length=50,unique=true,nullable=false)
    private String email;
    private String password;
    public void updatePw(String password){
        this.password = password;
    }
}
