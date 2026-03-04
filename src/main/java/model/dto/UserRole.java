package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRole {
    String id;
    String userId;
    String roleId;
    Boolean isLastLogin;
}
