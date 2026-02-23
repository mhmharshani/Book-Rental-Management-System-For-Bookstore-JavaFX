package model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    String id;
    String name;
    String address;
    String phoneNumber;
    List<Kid> kidsList;
}
