package king.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    //订单id
    private Integer order_id;
    //商品id
    private Integer article_id;
    private Integer amount;
}
