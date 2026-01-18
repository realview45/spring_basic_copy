package com.beyond.basic.board.author.controller;
import com.beyond.basic.board.author.dtos.AuthorCreateDto;
import com.beyond.basic.board.author.dtos.AuthorDetailDto;
import com.beyond.basic.board.author.dtos.AuthorListDto;
import com.beyond.basic.board.author.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    AuthorService authorService;
    public AuthorController(){
        authorService = new AuthorService();
    }
    @PostMapping("/create")
    public String create(@RequestBody AuthorCreateDto authorCreateDto){
        authorService.create(authorCreateDto);
        return "ok";
    }
    @GetMapping("/findAll")
    public List<AuthorListDto> findAll(){
        List<AuthorListDto> list = authorService.findAll();
        return list;
    }
    @GetMapping("/findById/{id}")
    public AuthorDetailDto findById(@PathVariable Long id){
        AuthorDetailDto dto = authorService.findById(id);
        return dto;
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        if(authorService.delete(id)){
            return "ok";
        }
        else{
            return "fail";
        }
    }
}
