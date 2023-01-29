package org.example.services;

import org.example.Entities.Order;
import org.example.Entities.Order;
import org.example.Entities.Product;
import org.example.Entities.User;
import org.example.dao.OrderDAO;
import org.example.dao.ProductDAO;

import java.lang.management.OperatingSystemMXBean;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private static final String SHIPPED_STATUS = "shipped";
    private static final String SHIPPING_STATUS = "shipping";
    private static final String RECEIVED_STATUS = "received";
    private final int SHIPPING_DAYS=10;
    private final String ASSEMBLING_STATUS="assembling";
    OrderDAO dao = new OrderDAO();
    CartService cartService = new CartService();
    public List<Order> findAll() {
        return dao.findAll();
    }
    public Order findOrderById(Long id) {

        return dao.findOrderById(id).get();
    }
    public List<Product> findOrderProducts(Long oid) {
        return ProductService.encodeImages(dao.findProductsByOid(oid));
    }
   public boolean delete(Long oid) {
        return dao.delete(oid);
    }

    public Order update(Order order) {

        return dao.update(order);
    }

    public boolean insertOrder(List<Product> userCart, String email, String shippingAddress, String phoneNumber) {
        Order order = new Order();
        order.setEmail(email);
        order.setPhoneNumber(phoneNumber);
        order.setAddress(shippingAddress);
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setStatus(ASSEMBLING_STATUS);
        Double totalSum = userCart.stream()
                .map(x -> x.getQuantity() * x.getPrice())
                .reduce(Double::sum)
                .orElse(0.0);
        order.setTotalSum(totalSum);
        cartService.deleteUserCart(email);
        Optional<Order> inserted = dao.insert(order);
        if (inserted.isEmpty()) return false;
        Long oid = inserted.get().getOid();
        for (Product p:userCart) {
            Order orderDetails = new Order();
            orderDetails.setOid(oid);
            orderDetails.setPid(p.getPid());
            orderDetails.setQuantity(p.getQuantity());
            if (!dao.insertDetails(orderDetails)) return false;
        }
        return true;
    }
    public boolean setOrderShipping(Long oid) {
        Optional<Order> byId = dao.findOrderById(oid);
        if (byId.isEmpty()) return false;
        Order order = byId.get();
        order.setStatus(SHIPPING_STATUS);
        order.setShippedDate(calculateShippingDate(order.getAddress()));
        dao.update(order);
        return true;
    }
    public boolean setOrderShipped(Long oid) {
        Optional<Order> byId = dao.findOrderById(oid);
        if (byId.isEmpty()) return false;
        Order order = byId.get();
        order.setStatus(SHIPPED_STATUS);
        order.setShippedDate(Date.valueOf(LocalDate.now()));
        dao.update(order);
        return true;
    }
    public boolean setOrderReceived(Long oid) {
        Optional<Order> byId = dao.findOrderById(oid);
        if (byId.isEmpty()) return false;
        Order order = byId.get();
        order.setStatus(RECEIVED_STATUS);
        order.setReceivedDate(Date.valueOf(LocalDate.now()));
        dao.update(order);
        return true;
    }
    private Date calculateShippingDate(String shippingAddress) {
        return Date.valueOf(LocalDate.now().plusDays(SHIPPING_DAYS));
    }

    public void process(Long oid) {
        Order order = findOrderById(oid);
        String status = order.getStatus();
        switch (status) {
            case ASSEMBLING_STATUS:
                setOrderShipping(oid);
                break;
            case SHIPPING_STATUS:
                setOrderShipped(oid);
                break;
            case SHIPPED_STATUS:
                setOrderReceived(oid);
                break;
        }
    }

    public List<Order> findAll(User user) {
        System.out.println(user.getRole());
        if (user.getRole().equals("ADMIN")) return dao.findAll();
        return dao.findOrdersByEmail(user.getEmail());
    }
}
