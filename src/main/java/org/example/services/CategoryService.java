package org.example.services;

import org.example.Entities.Category;
import org.apache.log4j.Logger;
import org.example.dao.CategoryDAO;

import java.util.List;
import java.util.Optional;

public class CategoryService {
    Logger log = Logger.getLogger(CategoryService.class.getName());
    CategoryDAO dao = new CategoryDAO();

    public Category insertCategory(Category category) {
        return dao.insert(category);
    }
    public List<Category> findAll() {
        return dao.findAll();
    }

    public Optional<Category> findEntityById(Long id) {
        return dao.findEntityById(id);
    }

    public boolean delete(Long id) {
        if (findEntityById(id).isEmpty()) return false;
        return dao.delete(id);
    }



    public Category update(Category category) {
        return dao.update(category);
    }

}
