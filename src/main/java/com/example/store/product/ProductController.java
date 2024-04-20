package com.example.store.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductService productService;

    //상품 삭제
    @PostMapping("/product/{id}/delete")
    public String deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
        //product/list를 반환하는 컨트롤러 존재 -> redirect 할 것
        //어려우면 PostMapping은 redirect로 준다고 생각하세요
        return "redirect:/product";
    }

    //상품 목록보기
    @GetMapping("/product")
    public String listForm(HttpServletRequest request) {
        List<Product> productList = productService.findAll();
        request.setAttribute("productList", productList);

        return "/product/list";
    }

    //상품 상세보기
    @GetMapping("/product/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        return "/product/detail";
    }

    // 상품 등록
    @PostMapping("/save")
    public String save(ProductRequest.SaveDTO requestDTO) {
        productService.save(requestDTO);
        return "redirect:/product";
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
