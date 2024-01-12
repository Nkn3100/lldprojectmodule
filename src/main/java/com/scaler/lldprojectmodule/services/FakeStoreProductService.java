package com.scaler.lldprojectmodule.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.lldprojectmodule.dtos.FakeStoreProductDTO;
import com.scaler.lldprojectmodule.exceptions.ProductNotFoundException;
import com.scaler.lldprojectmodule.models.Category;
import com.scaler.lldprojectmodule.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.apache.tomcat.util.net.SocketEvent.TIMEOUT;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        product.setDescription(productDTO.getDescription());
        product.setImageUrl(productDTO.getImage());
        return product;
    }
    private FakeStoreProductDTO convertProductToFakeStoreProductDTO(Product product) {
        FakeStoreProductDTO productDTO = new FakeStoreProductDTO();
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImageUrl());
        return productDTO;
    }
    private String constructPatchRequestBody(FakeStoreProductDTO productDTO,Product product){
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("{");
        if (product.getTitle() != null && !product.getTitle().equals(productDTO.getTitle()))

            requestBody.append("\"\":\"").append(product.getTitle()).append("\"");
        if (product.getPrice() != 0 && product.getPrice() != productDTO.getPrice())
            requestBody.append("\"price\":\"").append(product.getPrice()).append("\"");
        if (product.getCategory() != null && !product.getCategory().getName().equals(productDTO.getCategory()))
            requestBody.append("\"category\":\"").append(product.getCategory().getName()).append("\"");
        if (product.getDescription() != null && !product.getDescription().equals(productDTO.getDescription()))
            requestBody.append("\"description\":\"").append(product.getDescription()).append("\"");
        if (product.getImageUrl() != null && !product.getImageUrl().equals(productDTO.getImage()))
            requestBody.append("\"image\":\"").append(product.getImageUrl()).append("\"");
        requestBody.append("}");
        return requestBody.toString();

    }
    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {
        FakeStoreProductDTO productDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        if (productDTO == null){
            throw new ProductNotFoundException("Product not found for id: " + id);
        }
        return convertFakeStoreProductDTOToProduct(productDTO);
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDTO productDTO = convertProductToFakeStoreProductDTO(product);
        FakeStoreProductDTO responseDTO = restTemplate.postForObject("https://fakestoreapi.com/products", productDTO, FakeStoreProductDTO.class);
        if (responseDTO != null)
            return convertFakeStoreProductDTOToProduct(responseDTO);
        System.out.println("Product not created");
        return null;
    }

    @Override
    public String updateProduct(Long id, Product product) throws Exception {
        FakeStoreProductDTO productDTO = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);
        if (productDTO == null){
            throw new ProductNotFoundException("Product not found for id: " + id);
        }
        String requestBody = constructPatchRequestBody(productDTO,product);
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        String response = restTemplate.patchForObject("https://fakestoreapi.com/products/" + 111, requestBody, String.class);
        if (response != null)
            return response;
        throw new Exception("Product not updated");
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] productDTOS = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        if (productDTOS != null){
            List<Product> products = new java.util.ArrayList<>();
            for (FakeStoreProductDTO productDTO : productDTOS) {
                products.add(convertFakeStoreProductDTOToProduct(productDTO));
            }
            return products;
        }
        System.out.println("Products not found");
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        FakeStoreProductDTO responseSTO = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, FakeStoreProductDTO.class).getBody();
        if (responseSTO != null){
            System.out.println("Product deleted");
            return convertFakeStoreProductDTOToProduct(responseSTO);
        }
        System.out.println("Product not deleted");
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDTO productDTO = convertProductToFakeStoreProductDTO(product);
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(productDTO);
        FakeStoreProductDTO responseDTO = restTemplate.exchange("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestEntity, FakeStoreProductDTO.class).getBody();
        if (responseDTO != null){
            System.out.println("Product replaced");
            return convertFakeStoreProductDTOToProduct(responseDTO);
        }
        return null;
    }
}
