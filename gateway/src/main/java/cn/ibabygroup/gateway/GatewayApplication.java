package cn.ibabygroup.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by zengxiaobo on 2016/8/1 for netflixdemo
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class,args);
    }
}
