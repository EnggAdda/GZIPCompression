package org.example.gzipcompression;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<GzipFilter> gzipFilter() {
        FilterRegistrationBean<GzipFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GzipFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
   /* @Bean
    public FilterRegistrationBean<GzipRequestFilter> gzipRequestFilter() {
        FilterRegistrationBean<GzipRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GzipRequestFilter());
        registrationBean.addUrlPatterns("/*"); // Adjust this pattern to match your endpoints
        return registrationBean;
    }*/
}
