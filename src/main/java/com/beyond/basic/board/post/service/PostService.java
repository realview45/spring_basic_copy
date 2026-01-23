package com.beyond.basic.board.post.service;

import com.beyond.basic.board.author.domain.Author;
import com.beyond.basic.board.author.repository.AuthorRepository;
import com.beyond.basic.board.post.domain.Post;
import com.beyond.basic.board.post.dtos.PostCreateDto;
import com.beyond.basic.board.post.dtos.PostDetailDto;
import com.beyond.basic.board.post.dtos.PostListDto;
import com.beyond.basic.board.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    @Autowired
    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }
    public void create(PostCreateDto postCreateDto) {
        authorRepository.findByEmail(postCreateDto.getAuthorEmail()).orElseThrow(()-> new EntityNotFoundException("entity is not found"));
        Post post = postCreateDto.toEntity();
        postRepository.save(post);
    }
    @Transactional(readOnly=true)
    public List<PostListDto> findAll() {
        return postRepository.findAll().stream()
                .map(p->PostListDto.fromEntity(p)).collect(Collectors.toList());
    }
    @Transactional(readOnly=true)
    public PostDetailDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("entity is not found"));
//        Author author = authorRepository
//                .findByEmail(post.getAuthorEmail()).orElseThrow(()-> new EntityNotFoundException("entity is not found"));
        return PostDetailDto.fromEntity(post);
    }
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("entity is not found"));
        post.deletePost();
    }
}
