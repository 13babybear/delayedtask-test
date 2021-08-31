package cn.bounter.delaytask.test.controller;

import cn.bounter.delayedtask.starter.DelayedQueue;
import cn.bounter.delaytask.test.task.OrderPayTimeoutTask;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
     * @param timeout   支付超时时间，单位：秒
     */
    @GetMapping("/create")
    public void createOrder(Long orderId, Long timeout) {
        //设置订单创建后超时未支付就自动关闭订单
        //TODO：创建订单逻辑
        log.info("订单【{}】创建成功，等待支付...", orderId);

        //把任务加入'订单支付超时'延时队列，并设置超时时间
        delayedQueue.add(orderId, timeout, TimeUnit.SECONDS, OrderPayTimeoutTask.class);
    }
}
