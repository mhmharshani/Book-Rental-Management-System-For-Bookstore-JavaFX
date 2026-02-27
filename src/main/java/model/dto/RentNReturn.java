package model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentNReturn {
    String id;
    LocalDate issueDate;
    LocalDate dueDate;
    Double total;
    Boolean isAllReturned;
    String customerId;
    String userId;
    List<RentNReturnDetails> rentDetailsList;
}
