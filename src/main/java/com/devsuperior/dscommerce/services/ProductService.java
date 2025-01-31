package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dtos.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        return new ProductDTO(optionalProduct.get());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> products = repository.findAll(pageable);
        return products.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product product = new Product();

        BeanUtils.copyProperties(productDTO, product);
//        copyDtoToEntity(productDTO, product);

        product = repository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO){
        Product product = repository.getReferenceById(id);

//        Quando busca pelo getReferenceByID (Laze), é necessário utilizar os metodos get da classe
//        BeanUtils.copyProperties(productDTO, product);
        copyDtoToEntity(productDTO, product);
        product = repository.save(product);

        return new ProductDTO(product);
    }

    private static void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }
}
