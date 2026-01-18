package com.beyond.basic.board.author.service;

import com.beyond.basic.board.author.domain.Author;
import com.beyond.basic.board.author.dtos.AuthorCreateDto;
import com.beyond.basic.board.author.dtos.AuthorDetailDto;
import com.beyond.basic.board.author.dtos.AuthorListDto;
import com.beyond.basic.board.author.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AuthorService {
    AuthorRepository authorRepository;
    public AuthorService(){
        authorRepository = new AuthorRepository();
    }
    public void create(AuthorCreateDto authorCreateDto){
        Author author = new Author(null, authorCreateDto.getName(), authorCreateDto.getEmail(), authorCreateDto.getPassword());
        authorRepository.create(author);
    }
    public List<AuthorListDto> findAll(){
        List<Author> list = authorRepository.findAll();
        List<AuthorListDto> dtoList = new ArrayList<>();
        for(Author a : list){
            dtoList.add(new AuthorListDto(a.getId(),a.getName(),a.getEmail()));
        }
        return dtoList;
    }
    public AuthorDetailDto findById(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new NoSuchElementException("존재하지 않습니다."));
        return new AuthorDetailDto(author.getId(), author.getName(), author.getEmail(), author.getPassword());
    }
    public boolean delete(Long id){
        return authorRepository.delete(id);
    }
}
