package model.TM;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentNReturnTM {
    String id;
    String customerId;
    Double total;
    LocalDate issueDate;
    LocalDate dueDate;
    String returnStatus;
}
