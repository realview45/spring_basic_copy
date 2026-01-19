package com.beyond.basic.board.author.service;

import com.beyond.basic.board.author.domain.Author;
import com.beyond.basic.board.author.dtos.AuthorCreateDto;
import com.beyond.basic.board.author.dtos.AuthorDetailDto;
import com.beyond.basic.board.author.dtos.AuthorListDto;
import com.beyond.basic.board.author.repository.AuthorJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorJdbcRepository authorRepository;

    @Autowired
    public AuthorService(AuthorJdbcRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void create(AuthorCreateDto authorCreateDto) {
        Author author = Author.builder()
                        .name(authorCreateDto.getName())
                        .email(authorCreateDto.getEmail())
                        .password(authorCreateDto.getPassword())
                        .build();
        authorRepository.create(author);
    }

    public List<AuthorListDto> findAll() {
        return authorRepository.findAll().stream().map(a->AuthorListDto.builder()
                .id(a.getId())
                .name(a.getName())
                .email(a.getEmail())
                .build())
                .collect(Collectors.toList());
    }

    public AuthorDetailDto findById(Long id) {
        Author author =null;
        try {
            author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않습니다."));
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return AuthorDetailDto.builder()
                .id(author.getId())
                .email(author.getEmail())
                .name(author.getName())
                .password(author.getPassword())
                .build();
    }

    public boolean delete(Long id) {
        return authorRepository.delete(id);
    }
}
