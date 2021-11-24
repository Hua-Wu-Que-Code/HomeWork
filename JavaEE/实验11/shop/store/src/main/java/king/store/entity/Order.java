package king.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;     //订单id
    private String code;    //订单编号
    public Double total;    //订单总金额

    //订单和用户是一对一关系，一个订单只能属于一个用户
    private User user;

    //订单和商品是多对多关系，即一个订单可以包含多种商品
    private List<Article> articles;
}
