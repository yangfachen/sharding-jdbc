package com.yang.strategy;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Hint分片算法 (分库)
 * Hint分片策略HintShardingStrategy
 *
 * hint的中文意思：提示、暗示
 * 这种分片策略无需配置文件进行配置分片健，分片健值也不再从 SQL中解析，外部手动指定分片健或分片库，让 SQL在指定的分库、分表中执行
 * 通过Hint代码指定的方式而非SQL解析的方式分片的策略
 * Hint策略会绕过SQL解析的，对于这些比较复杂的需要分片的查询，Hint分片策略性能可能会更好
 * 可以指定sql去某个库某个表进行执行
 * @Classname CustomDBHintShardingAlgorithm
 * @Description TODO
 * @Date 2022/1/13 16:02
 * @Created by yangchen
 */
public class CustomDBHintShardingAlgorithm  implements HintShardingAlgorithm<Long> {
    /**
     *
     * @param dataSourceNames 数据源集合
     *                      在分库时值为所有分片库的集合 databaseNames
     *                      分表时为对应分片库中所有分片表的集合 tablesNames
     *
     * @param hintShardingValue  分片属性，包括
     *                                  logicTableName 为逻辑表，
     *                                  columnName 分片健（字段），hit策略此处为空 ""
     *
     *                                  value 【之前】都是 从 SQL 中解析出的分片健的值,用于取模判断
     *                                   HintShardingAlgorithm不再从SQL 解析中获取值，而是直接通过
     *                                   hintManager.addTableShardingValue("product_order", 1)参数进行指定
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> dataSourceNames,
                                         HintShardingValue<Long> hintShardingValue) {

        Collection<String> result = new ArrayList<>();
        for (String tableName : dataSourceNames) {

            for (Long shardingValue : hintShardingValue.getValues()) {

                if (tableName.endsWith(String.valueOf(shardingValue % dataSourceNames.size()))) {
                    result.add(tableName);
                }
            }

        }
        return result;
    }
}
