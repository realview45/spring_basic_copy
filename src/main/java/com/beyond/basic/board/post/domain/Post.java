package com.beyond.basic.board.post.domain;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @ToString
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length=3000,nullable=false)
    private String contents;
    private String category;
    @Column(length=50,unique=true,nullable=false)
    private String authorEmail;
    @Builder.Default
    private String delYn = "N";
    public void deletePost(){
        this.delYn = "Y";
    }

}
