import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.Application;
import com.yang.mapper.AdConfigMapper;
import com.yang.mapper.ProductOrderMapper;
import com.yang.model.AdConfigDO;
import com.yang.model.ProductOrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @Classname DbTest
 * @Description TODO
 * @Date 2022/1/12 15:19
 * @Created by yangchen
 */
@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = Application.class)
@Slf4j
public class DbTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private AdConfigMapper adConfigMapper;

    /**
     * 测试分表
     */
    @Test
    public void testSaveProductOrder(){

        for(int i=0;i<10;i++){
            ProductOrderDO productOrder = new ProductOrderDO();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("二当家i="+i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0,32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            productOrder.setUserId(Long.valueOf(i+""));
            productOrderMapper.insert(productOrder);
        }
    }

    /**
     * 测试分库分表
     */
    @Test
    public void testSaveProductOrder2(){

        Random random = new Random();
        for(int i=0;i<20;i++){
            ProductOrderDO productOrder = new ProductOrderDO();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("二当家i="+i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0,32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            productOrder.setUserId(Long.valueOf(random.nextInt(50)));
            productOrderMapper.insert(productOrder);
        }
    }

    /**
     * 测试广播表
     */
    @Test
    public void testSaveAdConfig(){

        for(int i=0;i<10;i++){
            AdConfigDO adConfigDO = new AdConfigDO();
            adConfigDO.setConfigKey(String.valueOf(i));
            adConfigDO.setConfigValue("value"+i);
            adConfigDO.setType("config");

            adConfigMapper.insert(adConfigDO);
        }
    }

    /**
     * 测试绑定表
     */
    @Test
    public void testBandingTable(){

        List<Object> list = productOrderMapper.listProductOrderDetail();
        System.out.println(list);

    }

    /**
     * 有分片键查询
     */
    @Test
    public void testPartitionKeySelect(){
//        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("id",1481197894973857794L));
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().in("id", Arrays.asList(1481197894973857794L,1481197894973857795L)));
    }

    /**
     * 无分片键查询
     */
    @Test
    public void testNoPartitionKeySelect(){
//        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("out_trade_no","82de6ecd-f487-442a-921f-19596e7a"));
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().in("out_trade_no", Arrays.asList("82de6ecd-f487-442a-921f-19596e7a","82de6ecd-f487-442a-921f-19596e7a")));
    }

    /**
     * 有分片键删除
     */
    @Test
    public void testPartitionKeyDelete(){
//        productOrderMapper.delete(new QueryWrapper<ProductOrderDO>().eq("id",1481197894973857794L));
        productOrderMapper.delete(new QueryWrapper<ProductOrderDO>().in("id", Arrays.asList(1481197894973857794L,1481197894973857795L)));
    }

    /**
     * 无分片键删除
     */
    @Test
    public void testNoPartitionKeyDelete(){
//        productOrderMapper.delete(new QueryWrapper<ProductOrderDO>().eq("out_trade_no","82de6ecd-f487-442a-921f-19596e7a"));
        productOrderMapper.delete(new QueryWrapper<ProductOrderDO>().in("out_trade_no", Arrays.asList("82de6ecd-f487-442a-921f-19596e7a","82de6ecd-f487-442a-921f-19596e7a")));
    }


    /**
     * 精准分表
     */
    @Test
    public void testSaveProductOrderPreciseShardingTables(){
        Random random = new Random();
        for(int i=0;i<10;i++){
            ProductOrderDO productOrder = new ProductOrderDO();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("000二当家i="+i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0,32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            int value = random.nextInt(100);
            productOrder.setUserId(Long.valueOf(value));
            productOrderMapper.insert(productOrder);
        }
    }

    /**
     * 测试精准分库分表
     */
    @Test
    public void testSaveProductOrderPreciseShardingTablesAndDataBases(){

        Random random = new Random();
        for(int i=0;i<20;i++){
            ProductOrderDO productOrder = new ProductOrderDO();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("PreciseShardingTablesAndDataBases二当家i="+i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0,32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            productOrder.setUserId(Long.valueOf(random.nextInt(50)));
            productOrderMapper.insert(productOrder);
        }
    }

    /**
     * 分片键范围分表
     */
    @Test
    public void testRangePartitionTables(){
//        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().between("id",1L,1L));

        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().between("id",1L,3L));
    }

    /**
     * 分片键范围分库分表
     */
    @Test
    public void testRangePartitionDatabases(){
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().between("user_id",1L,1L));

//        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().between("user_id",1L,3L));
    }

    /**
     * 非分片键范围查询
     */
    @Test
    public void testRangePartitionNoKey(){
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().between("out_trade_no",1L,1L));
    }

    /**
     * 复合分片算法
     */
    @Test
    public void testcomplexKeys(){
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("id",66L).eq("user_id",99L));
    }

    /**
     * Hint分片算法
     */
    @Test
    public void testHint(){
// 清除掉历史的规则
        HintManager.clear();
        //Hint分片策略必须要使用 HintManager工具类
        HintManager hintManager = HintManager.getInstance();
        // 设置库的分片健,value用于库分片取模，
        hintManager.addDatabaseShardingValue("product_order",1L);

        // 设置表的分片健,value用于表分片取模，
        //hintManager.addTableShardingValue("product_order", 7L);
        hintManager.addTableShardingValue("product_order", 0L);

        // 如果在读写分离数据库中，Hint 可以强制读主库（主从复制存在一定延时，但在业务场景中，可能更需要保证数据的实时性）
        //hintManager.setMasterRouteOnly();

        //对应的value只做查询，不做sql解析
        productOrderMapper.selectList(new QueryWrapper<ProductOrderDO>().eq("id", 66L));
    }


}
