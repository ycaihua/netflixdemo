package cn.ibabygroup.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * Created by zengxiaobo on 2016/8/1 for netflixdemo
 */
@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class MonitoringApplication {
    public static void main(String[] args){
        SpringApplication.run(MonitoringApplication.class,args);
    }
}
