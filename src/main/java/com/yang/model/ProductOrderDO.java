package com.yang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Classname ProductOrderDO
 * @Description TODO
 * @Date 2022/1/12 14:51
 * @Created by yangchen
 */
@Data
@TableName("product_order")
@EqualsAndHashCode(callSuper = false)
public class ProductOrderDO {

//    @TableId(value = "id", type = IdType.AUTO)
//    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String outTradeNo;

    private String state;

    private Date createTime;

    private Double payAmount;

    private String nickname;

    private Long userId;
}
