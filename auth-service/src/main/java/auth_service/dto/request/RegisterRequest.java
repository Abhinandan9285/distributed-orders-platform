package auth_service.dto.request;

import common_lib.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}