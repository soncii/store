import org.example.Entities.User;
import org.example.services.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService service = new UserService();
    @Test
    public void TestSelectById()  throws Exception{
        Optional<User> entityById = service.findEntityById("aliya@gmail.com");
        assertFalse(entityById.isEmpty());
        assertEquals("aliya@gmail.com",entityById.get().getEmail());
    }
//    @Test
//    public void TestInsertAndDelete() throws Exception {
//        User user = new User("test@gmail.com","123","damir",
//                "test","testland","tester");
//        assertEquals(user, service.insertUser(user));
//        Optional<User> entityById = service.findEntityById("test@gmail.com");
//        assertFalse(entityById.isEmpty());
//        assertEquals(user,entityById.get());
//        assertTrue(service.delete("test@gmail.com"));
//        assertTrue(service.findEntityById("test@gmail.com").isEmpty());
//
//    }
//    @Test
//    public void TestUpdate() throws Exception{
//        User user = new User("test@gmail.com","123","damir",
//                "test","testland","tester");
//        User mock = new User("test@gmail.com","123","damir",
//                "test","testland","tester");
//        service.insertUser(user);
//        user.setAddress("Astana");
//        user=service.update(user);
//        assertNotEquals(mock,user);
//        Optional<User> entityById = service.findEntityById(user.getEmail());
//        assertFalse(entityById.isEmpty());
//        assertNotEquals(mock,entityById.get());
//        assertEquals(user,entityById.get());
//        service.delete(user.getEmail());
//    }


}
