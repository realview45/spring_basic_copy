package com.beyond.basic.board.post.dtos;

import com.beyond.basic.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostListDto {
    private Long id;
    private String category;
    private String authorEmail;
    public static PostListDto fromEntity(Post post) {
        return PostListDto.builder()
                .id(post.getId())
                .category(post.getCategory())
                .authorEmail(post.getAuthorEmail())
                .build();
    }
}
