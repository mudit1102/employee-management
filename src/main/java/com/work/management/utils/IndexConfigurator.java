package com.work.management.utils;

import com.work.management.configuration.ElasticConfigProperties;
import javax.annotation.PostConstruct;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
final class IndexConfigurator {

  private static final Logger LOGGER = LoggerFactory.getLogger(IndexConfigurator.class);

  private final RestHighLevelClient client;
  private final ElasticConfigProperties elasticConfigProperties;

  @Autowired
  public IndexConfigurator(RestHighLevelClient client,
      ElasticConfigProperties elasticConfigProperties) {
    this.client = client;
    this.elasticConfigProperties = elasticConfigProperties;
  }

  @PostConstruct
  private void createIndexMapping() {
    try {

      final GetIndexRequest request = new GetIndexRequest(
          elasticConfigProperties.getIndex().getName());
      final boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
      if (!exists) {
        final CreateIndexRequest indexRequest = new CreateIndexRequest(
            elasticConfigProperties.getIndex().getName());
        indexRequest.settings(Settings.builder()
            .put("index.number_of_shards", elasticConfigProperties.getIndex().getShard())
            .put("index.number_of_replicas", elasticConfigProperties.getIndex().getReplica()));
        final CreateIndexResponse createIndexResponse = client.indices()
            .create(indexRequest, RequestOptions.DEFAULT);
        if (createIndexResponse.isAcknowledged() && createIndexResponse.isShardsAcknowledged()) {
          LOGGER
              .info("{} index created successfully", elasticConfigProperties.getIndex().getName());
        } else {
          LOGGER.debug("Failed to create {} index", elasticConfigProperties.getIndex().getName());
        }

        final PutMappingRequest mappingRequest = new PutMappingRequest(
            elasticConfigProperties.getIndex().getName());

        mappingRequest.source(createBuilderWithMapping());
        final AcknowledgedResponse putMappingResponse = client.indices()
            .putMapping(mappingRequest, RequestOptions.DEFAULT);

        if (putMappingResponse.isAcknowledged()) {
          LOGGER.info("Mapping of {} was successfully created",
              elasticConfigProperties.getIndex().getName());
        } else {
          LOGGER
              .debug("Creating mapping of {} failed", elasticConfigProperties.getIndex().getName());
        }
      }
    } catch (Exception ex) {
      LOGGER.error("An exception thrown in createIndexWithMapping method.", ex);
    }
  }

  private XContentBuilder createBuilderWithMapping() {
    try {
      final XContentBuilder builder = XContentFactory.jsonBuilder();
      builder.startObject();
      {
        builder.startObject("properties");
        {
          builder.startObject("firstName");
          {
            builder.field("type", "text");
          }
          builder.endObject();

          builder.startObject("lastName");
          {
            builder.field("type", "text");
          }
          builder.endObject();

          builder.startObject("userName");
          {
            builder.field("type", "text");
          }
          builder.endObject();

          builder.startObject("id");
          {
            builder.field("type", "integer");
          }
          builder.endObject();

          builder.startObject("teamId");
          {
            builder.field("type", "text");
          }
          builder.endObject();

          builder.startObject("manager");
          {
            builder.field("type", "integer");
          }
          builder.endObject();

          builder.startObject("age");
          {
            builder.field("type", "integer");
          }
          builder.endObject();

          builder.startObject("phoneNumber");
          {
            builder.field("type", "text");
          }
          builder.endObject();
        }
        builder.endObject();
      }
      builder.endObject();
      return builder;
    } catch (Exception ex) {
      LOGGER.error("An exception thrown in createBuilderWithMapping ", ex);
    }
    return null;
  }
}
