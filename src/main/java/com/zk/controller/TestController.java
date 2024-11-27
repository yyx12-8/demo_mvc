package com.zk.controller;

import com.zk.feign.TestFeign;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customer")
@RefreshScope
public class TestController {

    private final RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${version}")
    private String version;

    @Resource
    private TestFeign testFeign;

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str){
        return restTemplate.getForObject("http://cloud-provider/echo/" + str, String.class);
    }

    @RequestMapping("/test")
    public String test() {
        String str = testFeign.test();
        return str;
    }

    @RequestMapping("/version")
    public String getVersion() {
        return version;
    }


}
