package com.beyond.basic.board.author.service;

import com.beyond.basic.board.author.domain.Author;
import com.beyond.basic.board.author.dtos.AuthorCreateDto;
import com.beyond.basic.board.author.dtos.AuthorDetailDto;
import com.beyond.basic.board.author.dtos.AuthorListDto;
import com.beyond.basic.board.author.dtos.AuthorUpdatePwDto;
import com.beyond.basic.board.author.repository.AuthorJdbcRepository;
import com.beyond.basic.board.author.repository.AuthorMybatisRepository;
import com.beyond.basic.board.author.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public void create(AuthorCreateDto authorCreateDto) {
        if(authorRepository.findByEmail(authorCreateDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이메일중복입니다.");
        }
        Author author = Author.builder()
                        .name(authorCreateDto.getName())
                        .email(authorCreateDto.getEmail())
                        .password(authorCreateDto.getPassword())
                        .build();
        authorRepository.save(author);
    }
    @Transactional(readOnly=true)
    public List<AuthorListDto> findAll() {
        return authorRepository.findAll().stream().map(a->AuthorListDto.builder()
                .id(a.getId())
                .name(a.getName())
                .email(a.getEmail())
                .build())
                .collect(Collectors.toList());
    }
    @Transactional(readOnly=true)
    public AuthorDetailDto findById(Long id) {
        Author author =null;
        try {
            author = authorRepository.findById(id).orElseThrow(()
                    -> new NoSuchElementException("엔티티가 존재하지 않습니다."));
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
    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("엔티티가 존재하지 않습니다."));
        authorRepository.delete(author);
    }
    public void updatePw(AuthorUpdatePwDto dto){
        Author author = authorRepository.findByEmail(dto.getEmail()).orElseThrow(()
                -> new EntityNotFoundException("entity is not found"));
        author.updatePw(dto.getPassword());
        //authorRepository.save(author);
    }
}
