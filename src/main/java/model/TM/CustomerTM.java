package model.TM;

import lombok.*;
import model.dto.Kid;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerTM {
    String id;
    String name;
    String phoneNumber;
    String address;
}
