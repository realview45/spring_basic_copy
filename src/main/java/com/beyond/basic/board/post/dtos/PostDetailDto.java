package com.beyond.basic.board.post.dtos;

import com.beyond.basic.board.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailDto {
    private Long id;
    private String contents;
    private String category;
    private String authorEmail;
    public static PostDetailDto fromEntity(Post post) {
        return PostDetailDto.builder().id(post.getId())
                .contents(post.getContents())
                .category(post.getCategory())
                .authorEmail(post.getAuthorEmail())
                .build();
    }
}
