package az.sense.rasimkafesi.dtos.userDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDto {
    @NotBlank(message = "agilli ol")
    private String name;
    @NotBlank(message = "agilli ol")
    private String surname;
    @NotBlank(message = "agilli ol")
    private String email;
    @NotBlank(message = "agilli ol")
    private String password;
    @NotBlank(message = "agilli ol")
    private String repeatPassword;
    private Long roleId;

}
