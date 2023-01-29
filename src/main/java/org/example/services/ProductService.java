package org.example.services;

import org.example.Entities.Product;
import org.example.dao.ProductDAO;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {
    ProductDAO dao = new ProductDAO();
    public List<Product> findAll() {
        List<Product> all = dao.findAll();
        return encodeImages(all).stream().sorted().collect(Collectors.toList());
    }
    public List<Product> findAll(String category) {
        if (category==null) return encodeImages(dao.findAll());
        List<Product> all = dao.findAllByCategory(category);
        return encodeImages(all);
    }
    public List<Product> findAllByCategory(Long cid) {
        List<Product> all = dao.findAllByCategory(cid);
        return encodeImages(all);
    }
    public static List<Product> encodeImages(List<Product> all) {
        for (Product p: all) {
            p.setBase64(Base64.getEncoder().encodeToString(p.getImage()));
        }
        return all;
    }

    public Optional<Product> findEntityById(Long id) {
        Optional<Product> entityById = dao.findEntityById(id);
        if (entityById.isPresent()) {
            Product product = entityById.get();
            product.setBase64(Base64.getEncoder().encodeToString(product.getImage()));
            return Optional.of(product);
        }
        return entityById;

    }

    public Product insertUser(Product product, Part file) {
        try {
            InputStream fileContent = file.getInputStream();
            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);
            product.setImage(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dao.insert(product);
    }

    public boolean delete(Long pid) {
        return dao.delete(pid);
    }

    public Product update(Product product) {
        return dao.update(product);
    }
}
