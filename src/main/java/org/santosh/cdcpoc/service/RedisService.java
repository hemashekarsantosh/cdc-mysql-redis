package org.santosh.cdcpoc.service;

import org.santosh.cdcpoc.domain.redis.ClaimsRedis;

import java.util.List;

public interface RedisService {

    public List<ClaimsRedis> getAllClaims();

    public ClaimsRedis getClaim(String id);
}
