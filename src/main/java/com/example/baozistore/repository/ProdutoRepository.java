package com.example.baozistore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.baozistore.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
