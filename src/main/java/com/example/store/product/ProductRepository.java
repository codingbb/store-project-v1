package com.example.store.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;

    //상품 목록보기
    public List<Product> findAll() {
        String q = """
                select * from product_tb order by id asc 
                """;
        Query query = em.createNativeQuery(q, Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }

    //상품 등록
    public void save(ProductRequest.SaveDTO requestDTO) {
        String q = """
                insert into product_tb(name, price, qty, created_at) values (?, ?, ?, now())
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getName());
        query.setParameter(2, requestDTO.getPrice());
        query.setParameter(3, requestDTO.getQty());
        query.executeUpdate();

    }


}
