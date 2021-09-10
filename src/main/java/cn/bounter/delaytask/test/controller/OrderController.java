package cn.bounter.delaytask.test.controller;

import cn.bounter.delayedtask.starter.DelayedQueue;
import cn.bounter.delaytask.test.task.DeliveryNoticeTask;
import cn.bounter.delaytask.test.task.OrderPayTimeoutTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

    // 注入延时队列
    @Autowired
    private DelayedQueue delayedQueue;


    /**
     * 创建订单
     * @param orderId
     * @param payTimeout   支付超时时间，单位：秒
     * @param payTimeout   配送通知时间，单位：秒
     */
    @GetMapping("/create")
    public void createOrder(String orderId, Long payTimeout, Long deliveryTimeout) {
        //设置订单创建后超时未支付就自动关闭订单
        //TODO：创建订单逻辑
        log.info("订单【{}】创建成功，【{}】秒后即将通知配送员配送，【{}】秒后未支付自动超时关闭订单.", orderId, deliveryTimeout, payTimeout);

        //n秒后自动给配送员发送配送通知
        delayedQueue.add(orderId, deliveryTimeout, TimeUnit.SECONDS, DeliveryNoticeTask.class);

        //把任务加入'订单支付超时'延时队列，并设置超时时间
        delayedQueue.add(orderId, payTimeout, TimeUnit.SECONDS, OrderPayTimeoutTask.class);
    }
}
