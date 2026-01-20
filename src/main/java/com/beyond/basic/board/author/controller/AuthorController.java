package com.beyond.basic.board.author.controller;
import com.beyond.basic.board.author.common.CommonErrorDto;
import com.beyond.basic.board.author.dtos.AuthorCreateDto;
import com.beyond.basic.board.author.dtos.AuthorDetailDto;
import com.beyond.basic.board.author.dtos.AuthorListDto;
import com.beyond.basic.board.author.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping("/create")
    public String create(@RequestBody @Valid AuthorCreateDto authorCreateDto){
        authorService.create(authorCreateDto);
        return "ok";
    }
    @GetMapping("/findAll")
    public List<AuthorListDto> findAll(){
        List<AuthorListDto> list = authorService.findAll();
        return list;
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            AuthorDetailDto dto = authorService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch(NoSuchElementException e){
            e.printStackTrace();
            CommonErrorDto dto =
                    CommonErrorDto.builder()
                            .status("404")
                            .error_message(e.getMessage())
                            .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            authorService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        } catch(NoSuchElementException e){
            e.printStackTrace();
            CommonErrorDto dto =
                    CommonErrorDto.builder()
                    .status("404")
                    .error_message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }
    }
}
