package com.zk.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "cloud-provider")
public interface TestFeign {

    @RequestMapping("hello")
    public String test();
}
