package com.beyond.basic.b1_basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private String age;
    private List<Score> scores;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Score{
        String subject;
        int score;
    }
}
