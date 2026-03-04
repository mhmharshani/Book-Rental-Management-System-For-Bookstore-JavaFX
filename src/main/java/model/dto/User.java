package model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    String id;
    LocalDate createdAt;
    Boolean isActive;
}
