package io.selvaesra.eventful.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateLoadBalanced {

    @LoadBalanced
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }


}
