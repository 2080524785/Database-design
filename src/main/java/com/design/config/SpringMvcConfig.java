package com.design.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.design.controller","com.design.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
