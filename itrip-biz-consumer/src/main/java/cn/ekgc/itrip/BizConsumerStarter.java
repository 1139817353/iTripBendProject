package cn.ekgc.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <b>爱旅行-主业务模块消费者启动类</b>
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class BizConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(BizConsumerStarter.class,args);
	}
}
