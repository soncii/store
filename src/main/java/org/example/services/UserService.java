package org.example.services;

import org.example.Entities.User;
import org.apache.log4j.Logger;
import org.example.dao.UserDAO;

import java.util.List;
import java.util.Optional;

public class UserService {
    Logger log = Logger.getLogger(UserService.class.getName());
    UserDAO dao = new UserDAO();
    private final String ROLE_ADMIN = "ADMIN";
    private final String ROLE_USER = "USER";

    public User insertUser(User user) {
        user.setRole(ROLE_USER);
        return dao.insert(user);
    }
    public List<User> findAll() {
        return dao.findAll();
    }

    public Optional<User> findEntityById(String id) {
        return dao.findEntityById(id);
    }

    public boolean delete(String id) {
        if (findEntityById(id).isEmpty()) return false;
        return dao.delete(id);
    }



    public User update(User user) {
        return dao.update(user);
    }

    public String validate(User user) {
        return "";
    }
    public static String validateNumber(String phoneNumber) {
        if (phoneNumber.charAt(0)=='+' && phoneNumber.charAt(1)=='7'
                && phoneNumber.charAt(2)=='7' && phoneNumber.length()==12) {
            for (int i = 1; i < 12; i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    return "Phone number should consist only from digits and '+' in the beginning";
                }
            }
            return "";
        }
        if (phoneNumber.charAt(0)==8 && phoneNumber.charAt(1)=='7' && phoneNumber.length()==11) {
            for (int i = 0; i < 11; i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    return "Phone number should consist only from digits and '+' in the beginning";
                }
            }
            return "";
        }
        return "Enter valid 10-digit Kazakhstan number starting from +7 or 8";
    }
}
