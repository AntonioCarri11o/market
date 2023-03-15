package mx.edu.utez.market.services.subcategory;

import mx.edu.utez.market.models.subcategory.Subcategory;
import mx.edu.utez.market.models.subcategory.SubcategoryRepository;
import mx.edu.utez.market.models.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository repository;
    @Transactional(readOnly = true)
    public CustomResponse<List<Subcategory>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(), false, 200, "OK"
                );
    }
    @Transactional(readOnly = true)
    public CustomResponse<Subcategory> getOne(Long id){
        return  new CustomResponse<>(
                this.repository.findById(id).get(),false,200,"OK"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Subcategory>insert(Subcategory subcategory){
        if(this.repository.existsByName(subcategory.getName()))
            return new CustomResponse<>(null,true,400,"La subcategoria ya se aregistrado");

        return new CustomResponse<>(
                this.repository.saveAndFlush(subcategory),false,200,"Subcategoria registrada corretamente"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Subcategory> update(Subcategory subcategory){
        if(this.repository.existsById(subcategory.getId()))
            return new CustomResponse<>(null,true,400,"La subcategoria no existe");

        return new CustomResponse<>(
                this.repository.saveAndFlush(subcategory),false,200,"Subcategoria actualizada correctamente"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Subcategory subcategory){
        if(this.repository.existsById(subcategory.getId()))
            return new CustomResponse<>(null,true,400,"La subcategoria no existe");

        return new CustomResponse<>(
                this.repository.findById(subcategory.getStatus(),subcategory.getId()),false,200,"Subcategoria actualizada correctamente"
        );
    }
}
