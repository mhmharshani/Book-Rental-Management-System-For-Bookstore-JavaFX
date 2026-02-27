package model.TM;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTM {
    String rentId;
    String bookId;
    String title;
    Integer qty;
    Double total;
}
