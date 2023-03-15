package mx.edu.utez.market.services.category;

import mx.edu.utez.market.models.category.Category;
import mx.edu.utez.market.models.category.CategoryRepository;
import mx.edu.utez.market.models.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Transactional(readOnly = true)
    public CustomResponse<List<Category>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(), false, 200, "OK"
                );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Category> getOne(Long id){
        return  new CustomResponse<>(
                this.repository.findById(id).get(),false,200,"OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Category> insert(Category category){
        //IMPORTANTE: Revisar condiciones agregar else
        if(this.repository.existsByName(category.getName()))
            return new CustomResponse<>(null,true,400,"La categoria ya se ha registrado");

        return new CustomResponse<>(
                this.repository.saveAndFlush(category),false,200,"Categoria registrada corretamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Category> update(Category category){
        //IMPORTANTE: Revisar condiciones agregar else
        if(!this.repository.existsById(category.getId()))
            return new CustomResponse<>(null,true,400,"La categoria no existe");

        return new CustomResponse<>(
                this.repository.saveAndFlush(category),false,200,"Categoria actualizada correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Category category){
        if(this.repository.existsById(category.getId()))
            return new CustomResponse<>(null,true,400,"La categoria no existe");

        return new CustomResponse<>(
                this.repository.updateStatusVyId(category.getStatus(),category.getId()),false,200,"Categoria actualizada correctamente"
        );
    }
}
