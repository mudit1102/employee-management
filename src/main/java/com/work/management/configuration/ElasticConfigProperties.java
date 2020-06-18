package com.work.management.configuration;

import com.work.management.properties.Clients;
import com.work.management.properties.Index;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
@Data
public class ElasticConfigProperties {

  @NestedConfigurationProperty
  private final Clients clients = new Clients();

  @NestedConfigurationProperty
  private final Index index = new Index();
}
