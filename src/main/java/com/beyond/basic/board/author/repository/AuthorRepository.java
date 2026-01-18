package com.beyond.basic.board.author.repository;

import com.beyond.basic.board.author.domain.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepository {
    List<Author> authorList = new ArrayList<>();//임시 DB역할
    static long staticId = 0;
    public void create(Author author){
        author.setId(staticId++);
        authorList.add(author);
    }
    public List<Author> findAll(){
        return authorList;
    }
    public Optional<Author> findById(Long id){
        return authorList.stream().filter(a->a.getId()==id).findFirst();
    }
    public boolean delete(Long id){
        for(Author a : authorList){
            if(a.getId()==id){
                authorList.remove(a);
                return true;
            }
        }
        return false;
    }
}
