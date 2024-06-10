package org.santosh.cdcpoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class DebeziumConfig {

    @Bean
    public io.debezium.config.Configuration connectorConfig() {
        return io.debezium.config.Configuration.create()
                .with("name","cdc-poc")
                .with("connector.class","io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage","org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename","/tmp/offsets.dat")
                .with("offset.flush.interval.ms","60000")
                .with("database.hostname","localhost")
                .with("database.port","3305")
                .with("database.user","root")
                .with("database.password","root")
                .with("database.dbname","practice_db")
                .with("include.schema.changes","false")
                .with("database.server.id","12345")
                .with("database.server.name","cdc-poc")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "/tmp/dbhistory.dat")
                .with("topic.prefix","cdc_01")
                .with("schema.history.internal","io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename","/tmp/dbschema-history.dat")
                .build();
    }


}
