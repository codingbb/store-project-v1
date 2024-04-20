package com.example.store.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public String listForm() {

        return "/product/list";
    }

    @GetMapping("/product/{id}")
    public String detail(@PathVariable Integer id) {

        return "/product/detail/" + id;
    }

    // 상품 등록
    @PostMapping("/save")
    public String save(ProductRequest.SaveDTO requestDTO) {
        productService.save(requestDTO);
        return "/product/list";
    }


    @GetMapping("/product/save-form")
    public String saveForm() {

        return "/product/save-form";
    }

    @GetMapping("/product/update-form")
    public String updateForm() {

        return "/product/update-form";
    }

    @GetMapping("/")
    public String main() {

        return "/index";
    }

}
