package com.tobi.orderAndProductService.Service;


import com.tobi.orderAndProductService.Model.Enitity.Product;
import com.tobi.orderAndProductService.Model.Request.ProductRequest;
import com.tobi.orderAndProductService.Model.Request.ProductUpdateRequest;
import com.tobi.orderAndProductService.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private KafkaTemplate<String,Product> kafkaTemplate;

    private final int minimumStockQuantity = 20;


    public String addProduct(ProductRequest request) {

        Product product= new Product();
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        if(product.getQuantity() <= minimumStockQuantity){
            product.setProductStockLevel("Low");
        }else{
            product.setProductStockLevel("High");
        }

        repository.save(product);
        return "Product saved to the database.";
    }

    public String updateProduct(ProductUpdateRequest request, String name) {

        Product product= repository.findByName(name);
        if(product == null){
            return "The product couldn't be found in the database.";
        }
        product.setQuantity(request.getQuantity() + product.getQuantity());
        product.setPrice(request.getPrice());
        if(product.getQuantity() <= minimumStockQuantity){
            product.setProductStockLevel("Low");
        }else{
            product.setProductStockLevel("High");
        }
        repository.save(product);
        return "Product Updated..";


    }

    public List<Product> viewAllProduct() {
        List<Product> productList = repository.findAll();
        return productList;
    }

    public Product viewProduct(String name) {
        Product product = repository.findByName(name);

        if(product.getProductStockLevel().equalsIgnoreCase("Low")){
            // call on kafka to throw notification on product quantity

            kafkaTemplate.send("NotificationQuantity",product);


        }
        return product;

    }

    public String deleteProduct(String name) {
        Product product = Optional.ofNullable(repository.findByName(name)).
                orElseThrow(()-> new RuntimeException("The product wasn't found in the database"));

        return "Product has been deleted from the database";
    }

    @Transactional
    public void updateProductFromService(Product productToBeSaved) {
        String name = productToBeSaved.getName();
        repository.updateProduct(name,productToBeSaved.getQuantity(),productToBeSaved.getProductStockLevel());
    }

    public Product getProductByName(String name) {
        Product product = repository.findByName(name);
        return product;
    }
}
