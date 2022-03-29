package com.yang.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Classname ProductOrderItemDO
 * @Description TODO
 * @Date 2022/1/12 17:47
 * @Created by yangchen
 */
@Data
@TableName("product_order_item")
@EqualsAndHashCode(callSuper = false)
public class ProductOrderItemDO {

    private Long id;

    private Long productOrderId;

    private Long productId;

    private String productName;

    private Integer buyNum;

    private Long userId;
}
