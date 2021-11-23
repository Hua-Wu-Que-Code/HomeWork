package king.store.entity;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;     //商品id
    private String name;    //商品名称
    private Double price;   //商品价格
    private String remark;  //商品描述

    /**
     * 商品和订单是多对多的关系，即一种商品可以包含在多个订单中
     */
    private List<Order> orders;

    public Article() {
        super();
    }
}
