package com.beyond.basic.board.post.dtos;

import com.beyond.basic.board.author.domain.Author;
import com.beyond.basic.board.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PostCreateDto {
    @NotBlank(message = "제목이 없습니다.")
    private String title;
    @NotBlank(message = "내용이 없습니다.")
    private String contents;
    @NotBlank(message = "카테고리가 없습니다.")
    private String category;
    @NotBlank(message = "회원이 없습니다.")
    private String authorEmail;
    public Post toEntity() {
        return Post.builder()
                .contents(this.contents)
                .category(this.category)
                .authorEmail(this.authorEmail)
                .build();
    }
}
