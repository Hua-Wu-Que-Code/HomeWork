package king.store.service;

import king.store.entity.Article;
import king.store.entity.Order;
import king.store.mapper.ArticleMapper;
import king.store.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public Order findOrders() {
        return orderMapper.selectById(1);
    }
}
