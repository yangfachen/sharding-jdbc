package com.yang.strategy;

import com.yang.model.ProductOrderDO;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 范围分片算法
 * @Classname CustomRangeShardingAlgorithm
 * @Description TODO
 * @Date 2022/1/13 14:49
 * @Created by yangchen
 */
public class CustomRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> dataSourceNames, RangeShardingValue<Long> rangeShardingValue) {

        Set<String> result = new LinkedHashSet<>();
        // between 起始值
        Long lower = rangeShardingValue.getValueRange().lowerEndpoint();
        // between 结束值
        Long upper = rangeShardingValue.getValueRange().upperEndpoint();

        // 循环范围计算分库逻辑
        for (long i = lower; i <= upper; i++) {
            for (String databaseName : dataSourceNames) {
                if (databaseName.endsWith(i % dataSourceNames.size() + "")) {
                    result.add(databaseName);
                }
            }
        }
        return result;
    }
}
