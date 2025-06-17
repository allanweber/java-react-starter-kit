package com.example.backend.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataSeeder {

    private final ObjectMapper objectMapper;
    private final List<JpaRepository<?, ?>> repositories;
    private final SeederProperties seederProperties;

    @Bean
    @Transactional
    public CommandLineRunner seedData() {
        return args -> {
            if (!seederProperties.isEnabled()) {
                log.info("Data seeding is disabled");
                return;
            }

            try {
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:data/*.json");

                for (Resource resource : resources) {
                    String filename = resource.getFilename();
                    if (filename == null)
                        continue;

                    // Extract entity name from filename (e.g., "foods.json" -> "food")
                    String entityName = filename.substring(0, filename.lastIndexOf('.'));
                    if (entityName.endsWith("s")) {
                        entityName = entityName.substring(0, entityName.length() - 1);
                    }

                    // Find matching repository
                    JpaRepository<?, ?> repository = findRepositoryForEntity(entityName);
                    if (repository == null) {
                        log.warn("No repository found for entity: {}", entityName);
                        continue;
                    }

                    // Check if seeding is allowed for this repository
                    if (shouldSeedRepository(repository)) {
                        seedRepository(repository, resource);
                    } else {
                        log.info("Skipping seeding for {} as the table is not empty", entityName);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to seed data", e);
            }
        };
    }

    private boolean shouldSeedRepository(JpaRepository<?, ?> repository) {
        return repository.count() == 0;
    }

    private JpaRepository<?, ?> findRepositoryForEntity(String entityName) {
        return repositories.stream()
                .filter(repo -> {
                    try {
                        ResolvableType resolvableType = ResolvableType.forClass(repo.getClass())
                                .as(JpaRepository.class);
                        Class<?> entityType = resolvableType.getGeneric(0).resolve();

                        if (entityType != null) {
                            String entityTypeName = entityType.getSimpleName().toLowerCase();
                            return entityTypeName.equals(entityName.toLowerCase());
                        }
                        return false;
                    } catch (Exception e) {
                        log.error("Error finding repository for entity: {}", entityName, e);
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    private <T> void seedRepository(JpaRepository<T, ?> repository, Resource resource) {
        try {
            InputStream inputStream = resource.getInputStream();
            String jsonContent = new String(StreamUtils.copyToByteArray(inputStream));

            ResolvableType resolvableType = ResolvableType.forClass(repository.getClass())
                    .as(JpaRepository.class);
            Class<T> entityType = (Class<T>) resolvableType.getGeneric(0).resolve();

            List<T> entities = objectMapper.readValue(jsonContent,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, entityType));

            repository.saveAll(entities);
            log.info("Seeded {} entities from {}", entities.size(), resource.getFilename());
        } catch (IOException e) {
            log.error("Failed to seed data from {}", resource.getFilename(), e);
            throw new RuntimeException("Failed to seed data from " + resource.getFilename(), e);
        }
    }

    @Data
    @ConfigurationProperties(prefix = "app.seeder")
    @Configuration
    public static class SeederProperties {
        /**
         * Whether data seeding is enabled
         */
        private boolean enabled = true;

    }
}