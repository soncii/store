//import org.example.Entities.ProductDTO;
//import org.example.services.ProductService;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProductServiceTest {
//      ProductService service = new ProductService();
//      @Test
//      public void TestFindAll() throws Exception {
//            List<ProductDTO> all = service.findAll();
//            all.forEach(System.out::println);
//      }
//    @Test
//    public void TestSelectById()  throws Exception{
//        Optional<ProductDTO> entityById = service.findEntityById(1L);
//        assertFalse(entityById.isEmpty());
//        assertEquals(1L,entityById.get().getPid());
//    }
//    @Test
//    public void TestInsertAndDelete() throws Exception {
//        ProductDTO product = new ProductDTO("pillow","sporty pillow",6000,1L);
//        ProductDTO inserted = service.insertUser(product, file);
//        assertNotNull(inserted);
//        Optional<ProductDTO> entityById = service.findEntityById(inserted.getPid());
//        assertFalse(entityById.isEmpty());
//        assertEquals(inserted,entityById.get());
//        assertTrue(service.delete(inserted.getPid()));
//        assertTrue(service.findEntityById(inserted.getPid()).isEmpty());
//
//    }
//    @Test
//    public void TestUpdate() throws Exception{
//        ProductDTO product = new ProductDTO("pillow","sporty pillow",6000,1L);
//        ProductDTO mock = new ProductDTO("pillow","sporty pillow",6000,1L);
//        ProductDTO inserted = service.insertUser(product, file);
//        inserted.setPrice(2000.0);
//        ProductDTO updated = service.update(inserted);
//        assertNotEquals(product,updated);
//        assertEquals(inserted.getPid(),updated.getPid());
//        service.delete(product.getPid());
//
//    }
//
//
//}
