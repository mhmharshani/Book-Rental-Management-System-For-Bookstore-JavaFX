package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCredentials {
    String id;
    String userName;
    String password;
    String userId;
}
