package com.work.management.configuration;

import com.work.management.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfiguration {

  @Bean
  public AuditorAwareImpl<String> auditorProvider() {
    return new AuditorAwareImpl<String>();
  }
}
