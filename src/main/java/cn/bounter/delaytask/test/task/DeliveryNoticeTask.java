package cn.bounter.delaytask.test.task;

import cn.bounter.delayedtask.starter.IDelayedTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 新的订单配送通知任务
 */
@Slf4j
@Component
public class DeliveryNoticeTask implements IDelayedTask<String> {

    @Override
    public void execute(String orderId) {
        log.info("你有一条新的订单【{}】，请及时处理", orderId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
