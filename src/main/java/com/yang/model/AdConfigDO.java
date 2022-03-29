package com.yang.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Classname AdConfigDO
 * @Description TODO
 * @Date 2022/1/12 16:56
 * @Created by yangchen
 */
@Data
@TableName("ad_config")
@EqualsAndHashCode(callSuper = false)
public class AdConfigDO {

    private Long id;

    private String configKey;

    private String configValue;

    private String type;
}


