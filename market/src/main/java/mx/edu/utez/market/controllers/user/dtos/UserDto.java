package mx.edu.utez.market.controllers.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.market.models.user.User;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Long id;
    @NotEmpty
    private String username;
    private String password;
    private Boolean status;
    private String LastAccess;
    private Boolean blocked;
    private String token;
    public User castToUser(){
        return new User(getId(), getUsername(), getPassword(), getStatus(), getLastAccess(), getBlocked(), getToken(),null,null);
    }
}
