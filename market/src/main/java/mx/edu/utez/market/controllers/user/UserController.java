package mx.edu.utez.market.controllers.user;

import mx.edu.utez.market.controllers.user.dtos.UserDto;
import mx.edu.utez.market.models.user.User;
import mx.edu.utez.market.models.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api-market/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll(){
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>>getOne(@PathVariable("username")String username){
        return new ResponseEntity<>(this.userService.getByUsername(username),HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<User>> insert(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.insert(userDto.castToUser()),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<User>> update(@Valid @RequestBody UserDto userDto){
        return  new ResponseEntity<>(this.userService.update(userDto.castToUser()),HttpStatus.CREATED);
    }
    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> changeStatus(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.changeStatus(userDto.castToUser().getStatus(),userDto.getUsername()),HttpStatus.CREATED);
    }
}
