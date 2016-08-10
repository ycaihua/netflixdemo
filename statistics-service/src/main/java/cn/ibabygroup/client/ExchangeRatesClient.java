package cn.ibabygroup.client;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by zengxiaobo on 2016/8/5 for netflixdemo
 */
@FeignClient(url = "${rates.url}",name = "rates-client")
public interface ExchangeRatesClient {

}
