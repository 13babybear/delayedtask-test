package cn.bounter.delaytask.test.task;

import cn.bounter.delayedtask.starter.IDelayedTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderPayTimeoutTask implements IDelayedTask<Long> {

    @Override
    public void execute(Long orderId) {
        log.info("订单【{}】超时未支付，自动关闭订单", orderId);
    }
}
