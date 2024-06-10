package org.santosh.cdcpoc.controller;

import org.santosh.cdcpoc.domain.redis.ClaimsRedis;
import org.santosh.cdcpoc.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisController {

    private RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/claims")
    public List<ClaimsRedis> getAllClaims() {
        return redisService.getAllClaims();
    }

    @GetMapping("/claims/{id}")
    public ClaimsRedis getClaimById(@PathVariable String id) {
        return redisService.getClaim(id);
    }


}
