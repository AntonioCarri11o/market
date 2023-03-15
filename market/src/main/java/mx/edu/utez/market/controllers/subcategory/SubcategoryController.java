package mx.edu.utez.market.controllers.subcategory;

import mx.edu.utez.market.controllers.subcategory.dtos.SubcategoryDto;
import mx.edu.utez.market.services.subcategory.SubcategoryService;
import mx.edu.utez.market.models.subcategory.Subcategory;
import mx.edu.utez.market.models.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-market/subcategory")
@CrossOrigin(origins =  {"*"})
public class SubcategoryController {
    @Autowired
    private SubcategoryService service;
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Subcategory>>>getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public  ResponseEntity<CustomResponse<Subcategory>>insert(@RequestBody SubcategoryDto subcategoryDto){
        return new ResponseEntity<>(this.service.insert(subcategoryDto.castToSubcategory()),HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Subcategory>>
    update(@RequestBody SubcategoryDto subcategoryDto){
        return new ResponseEntity<>(this.service.update(subcategoryDto.castToSubcategory()),HttpStatus.CREATED);
    }
    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>>
    enableOrDisable(@RequestBody SubcategoryDto subcategoryDto){
        return new ResponseEntity<>(this.service.changeStatus(subcategoryDto.castToSubcategory()),HttpStatus.OK);
    }
}
