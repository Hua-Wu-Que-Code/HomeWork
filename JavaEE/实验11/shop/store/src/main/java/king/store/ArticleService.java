package king.store;

import king.store.entity.Article;
import king.store.entity.Order;
import king.store.mapper.ArticleMapper;
import king.store.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    OrderMapper orderMapper;

    public Order findArticle() {
        return orderMapper.selectById(1);
    }
}
