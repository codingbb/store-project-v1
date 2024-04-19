package com.example.store.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductService productService;

}
