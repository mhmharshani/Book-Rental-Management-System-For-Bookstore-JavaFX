package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    String id;
    String title;
    String authorId;
    String category;
    Double rentPrice;
    Integer stock;
}
