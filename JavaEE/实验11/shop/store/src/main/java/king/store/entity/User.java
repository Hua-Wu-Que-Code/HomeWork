package king.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;         //用户id
    private String username;    //用户名
    private String loginName;   //登录名
    private String passwd;      //密码
    private String phone;       //联系电话
    private String address;     //收货地址

    //用户和订单是一对多的关系，即一个用户可以有多个订单
    private List<Order> orders;
}
