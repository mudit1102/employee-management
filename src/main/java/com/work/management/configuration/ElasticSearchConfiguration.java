package com.work.management.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfiguration {

  private final ElasticConfigProperties elasticConfigProperties;

  @Autowired
  public ElasticSearchConfiguration(
      ElasticConfigProperties elasticConfigProperties) {
    this.elasticConfigProperties = elasticConfigProperties;
  }

  @Bean
  public RestHighLevelClient getRestClient() {
    return new RestHighLevelClient(RestClient.builder(
        new HttpHost(elasticConfigProperties.getClients().getHostname(),
            elasticConfigProperties.getClients().getHttpPort(),
            elasticConfigProperties.getClients().getScheme())));
  }

}
