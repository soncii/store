import org.example.Entities.Category;
import org.example.services.CategoryService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {
    CategoryService service = new CategoryService();
    @Test
    public void TestSelectAll()  throws Exception{
        List<Category> entityById = service.findAll();
        System.out.println(entityById);
    }
    @Test
    public void TestSelectById()  throws Exception{
        Optional<Category> entityById = service.findEntityById(1L);
        assertFalse(entityById.isEmpty());
        assertEquals("health",entityById.get().getCname());
    }
    @Test
    public void TestInsertAndDelete() throws Exception {
        Category category = new Category("техника");
        Category inserted = service.insertCategory(category);
        assertEquals(inserted.getCname(),category.getCname());
        assertTrue(service.delete(inserted.getCid()));
        assertTrue(service.findEntityById(inserted.getCid()).isEmpty());

    }
    @Test
    public void TestUpdate() throws Exception{
        Category category = new Category("тест");
        Category inserted = service.insertCategory(category);
        inserted.setCname("изменение");
        Category updated = service.update(inserted);
        assertNotNull(updated);
        assertEquals(inserted.getCid(),updated.getCid());
        assertNotEquals(category.getCname(),updated.getCname());
        service.delete(updated.getCid());
    }


}
