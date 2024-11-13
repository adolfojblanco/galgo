package galgo.com.backend.controllers;

import galgo.com.backend.dto.ApiResponse;
import galgo.com.backend.dto.CategoryDTO;
import galgo.com.backend.dto.ResponseRecord;
import galgo.com.backend.services.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseRecord> getAllEnabled() {
        List<CategoryDTO> categories = categoryService.findAllEnabled();
        return ResponseEntity.ok().body(ResponseRecord.builder().data(categories).build());
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<?> findById(@PathVariable Long categoryId) {
        Optional<CategoryDTO> catDTO = categoryService.findById(categoryId);
        if (catDTO.isPresent()) {
            return  ResponseEntity.ok(catDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDTO));
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateById(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        Optional<CategoryDTO> catDTO = categoryService.findById(categoryId);
        if (catDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.updateById(categoryDTO));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<ResponseRecord> categoryResponse() {
        return ResponseEntity.ok(ResponseRecord.builder().message("Hubo un error").status("201").build());
    }


    @PostMapping("/{categoryId}/enabled")
    public ResponseEntity<ApiResponse> enabledCategory(@PathVariable Long categoryId) {
        CategoryDTO categoryDTO = categoryService.enabledCategory(categoryId);
        ApiResponse response = new ApiResponse();
        response.setCode(201);
        response.setMessage( categoryDTO.getCategoryName() + " Actualizada correctamente");
        response.setData(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
