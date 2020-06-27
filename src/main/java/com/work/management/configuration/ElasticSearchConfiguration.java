package com.work.management.configuration;

import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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

  @Bean
  public SearchSourceBuilder getSearchSourceBuilder() {
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.from(elasticConfigProperties.getIndex().getFrom());
    sourceBuilder.size(elasticConfigProperties.getIndex().getSize());
    sourceBuilder
        .timeout(new TimeValue(elasticConfigProperties.getIndex().getTimeout(), TimeUnit.SECONDS));

    return sourceBuilder;
  }
  @Bean
  public Gson getGson(){
    return new Gson();
  }
}
