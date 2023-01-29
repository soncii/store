package org.example.services;

import org.example.Entities.Category;
import org.apache.log4j.Logger;
import org.example.Entities.Product;
import org.example.Entities.User;
import org.example.dao.CategoryDAO;
import org.example.dao.ProductDAO;
import org.example.dao.CartDAO;

import java.util.List;
import java.util.Optional;

public class CartService {
    Logger log = Logger.getLogger(CartService.class.getName());
    CartDAO dao = new CartDAO();

    public List<Product> findUserCart(String email) {
        List<Product> cartByEmail = dao.findCartByEmail(email);
        ProductService.encodeImages(cartByEmail);
        return cartByEmail;
    }

    public boolean deleteProductFromCart(String email, Long pid) {
        if (dao.findById(email,pid).isEmpty()) return false;
        return dao.deleteFromCart(email, pid);
    }
    public boolean deleteUserCart(String email) {
        if (dao.findCartByEmail(email).isEmpty()) return false;
        return dao.deleteUserCart(email);
    }
    public boolean addToCart(String email, Long pid) {
        Optional<Product> cart = dao.findById(email, pid);
        if (cart.isEmpty()) return dao.insert(email, pid);
        return dao.update(email,pid, cart.get().getQuantity()+1);
    }

    public boolean updateQuantity(String email, Long pid, Long quantity) {
        Optional<Product> product = dao.findById(email, pid);
        if (product.isEmpty()) return false;
        if (product.get().getQuantity().equals(quantity)) return false;
        return dao.update(email,pid,quantity);
    }

    public static Double calculateTotal(List<Product> userCart) {
        Optional<Double> reduce = userCart.stream().map(x -> x.getPrice() * x.getQuantity()).reduce(Double::sum);
        if (reduce.isEmpty()) return 0.0;
        return reduce.get();
    }


}
