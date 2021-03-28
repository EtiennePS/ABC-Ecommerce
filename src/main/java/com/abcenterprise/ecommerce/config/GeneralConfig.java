package com.abcenterprise.ecommerce.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import com.abcenterprise.ecommerce.model.entity.User;
import com.abcenterprise.ecommerce.repository.UserRepository;

@Configuration
public class GeneralConfig {
	
	@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
	
	/*@Bean
	public ApplicationRunner initializer(UserRepository repository) {
	    return args -> repository.saveAll(Arrays.asList(
	        new User()
	    ));
	}*/
}
