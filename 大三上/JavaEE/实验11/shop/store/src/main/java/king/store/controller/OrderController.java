package king.store.controller;

import king.store.entity.Order;
import king.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @RequestMapping("/order")
    public Order findOrders() {
        return orderService.findOrders();
    }
}
