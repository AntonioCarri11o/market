package mx.edu.utez.market.controllers.user;

import mx.edu.utez.market.models.person.PersonRepository;
import mx.edu.utez.market.models.user.User;
import mx.edu.utez.market.models.user.UserRepository;
import mx.edu.utez.market.models.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonRepository personRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> findAll(){
        return new CustomResponse<>(this.userRepository.findAll(),false,200,"OK");
    }

    @Transactional(readOnly = true)
    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional(rollbackFor = {SQLDataException.class})
    public CustomResponse<User> insert(User user){
        if(this.userRepository.existsByUsername(user.getUsername()))
            return new CustomResponse<>(null,true,400,"Error nombre de usuario ya registrado");
        return new CustomResponse<>(this.userRepository.saveAndFlush(user),false,200,"OK");
    }

    @Transactional(rollbackFor = {SQLDataException.class})
    public CustomResponse<User> update(User user){
        if(!this.userRepository.existsByUsername(user.getUsername()))
            return new CustomResponse<>(null,true,400,"Error usuario no encontrado");
        return new CustomResponse<>(this.userRepository.saveAndFlush(user),false,200,"Usuario Actualizado!");
    }

    @Transactional(rollbackFor = {SQLDataException.class})
    public CustomResponse<Boolean> changeStatus(Boolean status,String username){
        if(!this.userRepository.existsByUsername(username))
            return new CustomResponse<>(null,true,400,"Error usuario no encotrado");
        return new CustomResponse<>(this.userRepository.changeStatus(status,username),false,200,"status actualizado!");
    }
}
