package com.winnguyen1905.technologystore.controller;

import com.winnguyen1905.technologystore.common.SystemConstant;
import com.winnguyen1905.technologystore.exception.CustomRuntimeException;
import com.winnguyen1905.technologystore.model.dto.ProductDTO;
import com.winnguyen1905.technologystore.model.request.AddProductRequest;
import com.winnguyen1905.technologystore.model.request.SearchProductRequest;
import com.winnguyen1905.technologystore.service.IProductService;
import com.winnguyen1905.technologystore.util.SecurityUtils;
import com.winnguyen1905.technologystore.util.annotation.MetaMessage;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("${release.api.prefix}/products")
public class ProductController {
    
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    
    // PUBLIC API----------------------------------------------------------------

    @GetMapping("/")
    @MetaMessage(message = "Get all product with filter success")
    public ResponseEntity<ProductDTO> getAllProducts(Pageable pageable,
        @ModelAttribute(SystemConstant.MODEL) SearchProductRequest productSearchRequest
    ) {
        productSearchRequest.setIsDraft(false);
        productSearchRequest.setIsPublished(true);
        return ResponseEntity.ok(this.productService.handleGetAllProducts(productSearchRequest, pageable));
    }

    @GetMapping("/{id}")
    @MetaMessage(message = "get product with by id success")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(this.productService.handleGetProduct(id));
    }

    // API FOR SHOP OWNER---------------------------------------------------------

    @PostMapping
    @MetaMessage(message = "add new product success")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductRequest productRequest) {
        UUID userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found userId", 403));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(this.productService.handleAddProduct(productRequest, userId));
    }

    @GetMapping("/my-product")
    @MetaMessage(message = "get all my product with filter success")
    public ResponseEntity<ProductDTO> getAllMyProducts(Pageable pageable,
        @ModelAttribute(SystemConstant.MODEL) SearchProductRequest productSearchRequest
    ) {
        String shopOwner = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new CustomRuntimeException("Not found username", 403));
        productSearchRequest.setCreatedBy(shopOwner);
        return ResponseEntity.ok(this.productService.handleGetAllProducts(productSearchRequest, pageable));
    }

    @PatchMapping
    @MetaMessage(message = "get all my product with filter success")
    public ResponseEntity<List<ProductDTO>> updateProducts(@RequestBody List<AddProductRequest> productRequests) {
        UUID userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found userId", 403));
        return ResponseEntity.ok(this.productService.handleUpdateManyProducts(productRequests, userId));
    }

    @PatchMapping("/change-status/{ids}")
    @MetaMessage(message = "Change visible products status success")
    public ResponseEntity<List<ProductDTO>> publishProducts(@PathVariable List<UUID> ids) {
        UUID userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found userId", 403));
        return ResponseEntity.ok(this.productService.handleChangeProductStatus(ids, userId));
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<Void> getMethodName(@PathVariable Set<UUID> ids) {
        UUID shopId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new CustomRuntimeException("Not found userId", 403));
        this.productService.handleDeleteProducts(List.copyOf(ids), shopId);
        return ResponseEntity.noContent().build();
    }

    // API FOR SHOP ADMIN---------------------------------------------------------
}