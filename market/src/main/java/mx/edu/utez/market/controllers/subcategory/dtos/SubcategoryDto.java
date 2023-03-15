package mx.edu.utez.market.controllers.subcategory.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.market.models.subcategory.Subcategory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubcategoryDto {
    private Long id;
    @NotEmpty(message = "Campo Obligatorio")
    @Size(min = 4, max = 150, message = "Debe ser entre 4 y 150 caracteres")
    private String name;

    private Boolean status;

    public Subcategory castToSubcategory(){
        return new Subcategory(getId(), getName(), getStatus(),null);
    }
}
