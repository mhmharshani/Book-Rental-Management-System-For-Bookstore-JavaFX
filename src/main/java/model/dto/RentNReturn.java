package model.dto;

import java.time.LocalDate;

public class RentNReturn {
    String id;
    LocalDate issueDate;
    LocalDate dueDate;
    Integer qty;
    Double total;
    Boolean isAllReturned;
    String customerId;
    String userId;
}
