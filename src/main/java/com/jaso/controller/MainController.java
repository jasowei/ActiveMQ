package com.jaso.controller;

import com.jaso.service.ConsumerService;
import com.jaso.service.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * Created by dllo on 17/11/29.
 */

@Controller
public class MainController {

    @Resource
    private Destination queueDestination;
    @Resource
    private ProducerService producerService;
    @Resource
    private ConsumerService consumerService;

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/producer")
    public String producer(){
        return "producer";
    }

    /**
     * 接收消息
     */
    @RequestMapping(value = "/consumer")
    public String consumer(Model model) throws JMSException {
        TextMessage message = consumerService.receive(queueDestination);
        if (message != null){
            model.addAttribute("message",message.getText());
        }else {
            model.addAttribute("message","无消息!");
        }
        return "consumer";
    }

    /**
     * 发送消息
     */
    @RequestMapping(value = "/sendMessage")
    public String sendMessage(@RequestParam("message")String msg){
        System.out.println("------消息发送到jms------");
        producerService.sendMessage(queueDestination,msg);
        return "home";
    }







}


/**
 消息中间件(消息队列/MQ/MessageQueue)

 1. 消息中间件在大型的电子商务类网站(淘宝,京东...)有深入的应用,
    主要作用 :消除高并发访问高峰, 加快网站的响应速度.
    如果不用消息队列, 用户的请求数据直接写入到数据库, 在高并发的情况下,
    会对数据库造成巨大的压力, 也会造成巨大的延迟.
    使用消息队列的情况下, 用户的请求发给队列之后立即返回确认信息, 然后由MQ
    的消费者进程从队列中获取信息, 异步写入数据库.

    消息队列对数据的处理速度远超数据库, 所以可以有效的解决相应延迟的问题.
    [削峰]

 2. 什么时候使用中间件?
    1> 系统整合: 分布式环境中.
    2> 降低模块之间的耦合: 其中的某个模块性能瓶颈/宕机, 不影响其他模块正常使用.
    3> 实现异步: 1.推消息 2.削峰
    4> 数据同步

 3. 中间件的特性
    1> 多语言/跨平台支持: java;c;c++;c#;Ruby;Python;Perl;PHP
    2> 高性能
    3> 多项目支持


 */



