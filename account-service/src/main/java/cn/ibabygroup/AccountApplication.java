package cn.ibabygroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by zengxiaobo on 2016/8/9 for netflixdemo
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AccountApplication {
    public static void main(String[] args){
        SpringApplication.run(AccountApplication.class,args);
    }
}
