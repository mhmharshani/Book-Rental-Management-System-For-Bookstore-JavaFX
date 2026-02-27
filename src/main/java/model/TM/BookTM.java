package model.TM;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookTM {
    String id;
    String title;
    String authorId;
    String category;
    Double rentPrice;
    Integer stock;
}
