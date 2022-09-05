package com.example.order_service.controller;

import com.example.order_service.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        Map<String,Object> data = new HashMap<>();
        data.put("code",0);
        data.put("data",productOrderService.save(userId,productId));
        return data;
    }
    private Object saveOrderFail(int userId,int productId){//方法必须与api方法一致
        String saveOrderKey = "save-order";
        String sendValue = redisTemplate.opsForValue().get(saveOrderKey);
        new Thread(()->{
            if(StringUtils.isBlank(sendValue)){
                System.out.println("用户下单失败，请查找原因");
                //TODO 发送日志信息
                redisTemplate.opsForValue().set(saveOrderKey,"delivered",20, TimeUnit.SECONDS);
            }else{
                System.out.println("已发送信息，20s内不重复发送");
            }
        }).start();

        Map<String,Object> msg = new HashMap<>();
        msg.put("code",-1);
        msg.put("msg","当前人数过多，请稍后重试");
        return msg;
    }
}
