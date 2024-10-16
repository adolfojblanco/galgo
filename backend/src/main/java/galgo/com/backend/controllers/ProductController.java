package galgo.com.backend.controllers;


import galgo.com.backend.dto.ProductDTO;
import galgo.com.backend.dto.ResponseRecord;
import galgo.com.backend.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<ResponseRecord> findAll(@PathVariable Long restaurantId) {
        List< ProductDTO> products = this.productService.findAll();
        return ResponseEntity.ok(ResponseRecord.builder().data(products).build());
    }
}
