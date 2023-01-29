import org.example.Entities.Rating;
import org.example.services.RatingService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingServiceTest {
    RatingService service = new RatingService();
    @Test
    public void TestFindAll() throws Exception {
        List<Rating> all = service.findAll();
        all.forEach(System.out::println);
    }
    @Test
    public void TestSelectById()  throws Exception{
        Optional<Rating> entityById = service.findEntityById(2L);
        assertFalse(entityById.isEmpty());
        assertEquals(2L,entityById.get().getRid());
    }
    @Test
    public void TestInsertAndDelete() throws Exception {
        Rating rating = new Rating(1L,"damir@gmail.com",10);
        Rating inserted = service.insertRating(rating);
        assertNotNull(inserted);
        Optional<Rating> entityById = service.findEntityById(inserted.getRid());
        assertFalse(entityById.isEmpty());
        assertEquals(inserted,entityById.get());
        assertTrue(service.delete(inserted.getRid()));
        assertTrue(service.findEntityById(inserted.getRid()).isEmpty());

    }
    @Test
    public void TestUpdate() throws Exception{
        Rating rating = new Rating(1L,"damir@gmail.com",10);
        Rating mock = new Rating(1L,"damir@gmail.com",10);
        Rating inserted = service.insertRating(rating);
        inserted.setRating(9);
        Rating updated = service.update(inserted);
        assertNotEquals(rating,updated);
        assertEquals(inserted.getRid(),updated.getRid());
        service.delete(inserted.getRid());

    }
}
