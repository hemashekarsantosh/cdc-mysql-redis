package org.santosh.cdcpoc.domain.redis;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Claims")

@RequiredArgsConstructor
@Getter
@Setter
public class ClaimsRedis implements Serializable {

    @Id
    private String id;
    private String claimId;
    private String type;
    private String amount;
}
