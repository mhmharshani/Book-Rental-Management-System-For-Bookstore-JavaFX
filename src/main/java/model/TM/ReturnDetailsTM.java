package model.TM;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnDetailsTM {
    String bookId;
    String rentId;
    Integer qty;
    Integer overdueDays;
    Double fine;
}
