package com.generation.crudfarmacia.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.crudfarmacia.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
