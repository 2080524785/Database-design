import com.alibaba.fastjson.JSONObject;
import com.design.config.SpringConfig;
import com.design.domain.Book;
import com.design.service.QuantityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= SpringConfig.class)
public class QuantityServiceTest {

    @Autowired
    private QuantityService quantityService;

    @Test
    public void testGetBorrow(){

        List<Book.BookNum> bookNumlist = quantityService.getBorrowInfo();
        Integer count = bookNumlist.size();
        System.out.println(bookNumlist);
    }
}
