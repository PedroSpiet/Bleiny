package com.bleiny.communities.adapters.configurations;

import com.bleiny.communities.CommunitiesApplication;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.services.CommunityServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CommunitiesApplication.class)
public class BeanConfiguration {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommunityServiceImpl communityService(CommunityRepositoryPort repository, ModelMapper modelMapper) {
        return new CommunityServiceImpl(repository, modelMapper);
    }
}
