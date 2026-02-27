package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    String refId;
    String rentId;
    String method;
    Double billAmount;
    String status;

}
