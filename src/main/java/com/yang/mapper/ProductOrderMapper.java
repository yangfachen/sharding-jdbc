package com.yang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.model.ProductOrderDO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname ProductOrderMapper
 * @Description TODO
 * @Date 2022/1/12 14:57
 * @Created by yangchen
 */
public interface ProductOrderMapper extends BaseMapper<ProductOrderDO> {

    @Select("select * from product_order o left join product_order_item i on o.id = i.product_order_id ")
     List<Object> listProductOrderDetail();
}
