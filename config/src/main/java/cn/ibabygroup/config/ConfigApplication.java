package cn.ibabygroup.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by zengxiaobo on 2016/7/29 for netflixdemo
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigApplication {
    public static void main(String[] args){
        SpringApplication.run(ConfigApplication.class,args);
    }
}
