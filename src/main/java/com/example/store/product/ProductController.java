package com.example.store.product;

import com.example.store._core.utils.ApiUtil;
import com.example.store.pic.PicRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
        List<ProductResponse.ListDTO> productList = productService.findAllList();
        request.setAttribute("productList", productList);

        return "/product/list";
    }

    //상품 상세보기
    @GetMapping("/product/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        ProductResponse.DetailDTO product = productService.findByIdDetail(id);
        request.setAttribute("product", product);

        System.out.println(product);
        return "/product/detail";
    }

    // 상품 등록
    @PostMapping("/product/save")
    public String save(ProductRequest.SaveDTO requestDTO) {
        System.out.println(requestDTO);

        MultipartFile imgFile = requestDTO.getImgFile();
        String imgFileName = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();

//        Path imgPath = Paths.get("./src/main/resources/static/upload/" + imgFileName);
        Path imgPath = Paths.get("./upload/" + imgFileName);

        try {
            //upload 디렉토리가 존재하지 않는다면, 서버가 시작될 때 해당 디렉토리를 자동으로 생성하는 코드
            //static에 안 넣으려고 설정해줬나봄
            Files.createDirectories(imgPath.getParent());
            Files.write(imgPath, imgFile.getBytes());
            productService.save(requestDTO, imgFileName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/product";
    }


    @GetMapping("/product/save-form")
    public String saveForm() {

        return "/product/save-form";
    }

    //업데이트 폼 (업데이트는 2개가 나와야합니다 ^^)
    @GetMapping("/product/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        ProductResponse.UpdateDTO product = productService.findByIdUpdate(id);
        request.setAttribute("product", product);
        return "/product/update-form";
    }

    //업데이트
    @PostMapping("/product/{id}/update")
    public String update(@PathVariable Integer id, ProductRequest.UpdateDTO requestDTO) {
        productService.updateById(id, requestDTO);
        return "redirect:/product";
    }


    @GetMapping("/")
    public String main(HttpServletRequest request) {
        List<ProductResponse.MainDTO> productList = productService.findAllMain();
        System.out.println(productList);
        request.setAttribute("productList", productList);
        return "/index";
    }

    //상품명 실시간 중복체크
    @GetMapping("/product/name-check")
    public @ResponseBody ResponseEntity<?> nameSameCheck(String name) {
        Product product = productService.findByName(name);
        if (product == null) {
            return ResponseEntity.ok(new ApiUtil<>(true)); //상품 등록 가능
        } else {
            //response.setStatus(400);
            return ResponseEntity.ok(new ApiUtil<>(false)); //상품 등록 불가
        }
    }

    //상품명 실시간 중복체크 (업데이트용)
    @GetMapping("/product/name-check/update")
    public @ResponseBody ResponseEntity<?> nameSameCheckUpdate(String name, Integer id) {
        Product product = productService.findByNameUpdate(name, id);
        if (product == null) {
            return ResponseEntity.ok(new ApiUtil<>(true)); //상품 등록 가능
        } else {
            return ResponseEntity.ok(new ApiUtil<>(false)); //상품 등록 불가
        }
    }


}
