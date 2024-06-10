package org.santosh.cdcpoc.listner;

import io.debezium.config.Configuration;
import io.debezium.data.Envelope;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.santosh.cdcpoc.domain.redis.ClaimsRedis;
import org.santosh.cdcpoc.repo.redis.ClaimsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.debezium.data.Envelope.FieldName.AFTER;
import static io.debezium.data.Envelope.FieldName.BEFORE;

@Slf4j
@Component
public class DebeziumSourceEventListener {

    private final Executor executor;

    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    @Autowired
    ClaimsRepo claimsRepo;

    public DebeziumSourceEventListener(Configuration configuration) {
        this.executor = Executors.newSingleThreadExecutor();
        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class)).using(configuration.asProperties()).notifying(this::handleChangeEvent).build();

    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        var sourceRecord = sourceRecordRecordChangeEvent.record();
        log.info("Key = {}, Value = {}", sourceRecord.key(), sourceRecord.value());
        var sourceRecordChangeValue = (Struct) sourceRecord.value();
        log.info("SourceRecordChangeValue = '{}'", sourceRecordChangeValue);
        log.info("Operation = '{}'", Envelope.Operation.forCode((String) sourceRecordChangeValue.get("op")));
        Envelope.Operation operation = Envelope.Operation.forCode((String) sourceRecordChangeValue.get("op"));

        if (operation != Envelope.Operation.READ) {
            String record = operation == Envelope.Operation.DELETE ? BEFORE : AFTER;
            Struct struct = (Struct) sourceRecordChangeValue.get(record);
            Map<String, Object> payload = struct.schema().fields().stream().map(Field::name).filter(s -> struct.get(s) != null).map(s -> Pair.of(s, struct.get(s))).collect(Pair.toMap());
            log.info("Payload - {}", payload);
            ClaimsRedis claimsRedis = new ClaimsRedis();
            claimsRedis.setId(payload.get("ClaimId").toString());
            claimsRedis.setType(payload.get("Type").toString());
            claimsRedis.setAmount(payload.get("Amount").toString());
            claimsRepo.save(claimsRedis);


        }


    }


    @PostConstruct
    private void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (this.debeziumEngine != null) {
            this.debeziumEngine.close();
        }
    }
}
