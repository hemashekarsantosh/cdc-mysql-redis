package org.santosh.cdcpoc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.santosh.cdcpoc.domain.redis.ClaimsRedis;
import org.santosh.cdcpoc.repo.redis.ClaimsRepo;
import org.santosh.cdcpoc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {


    private ClaimsRepo claimsRepo;

    private RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public RedisServiceImpl(ClaimsRepo claimsRepo, RedisTemplate<String, Object> redisTemplate) {
        this.claimsRepo = claimsRepo;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<ClaimsRedis> getAllClaims() {
        List<ClaimsRedis> claimsRedis = new ArrayList<ClaimsRedis>();
        claimsRepo.findAll().forEach(claimsRedis::add);
        // log.info("Size - {}",redisTemplate.opsForHash().entries("Claims").size());
        return claimsRedis;
    }

    @Override
    public ClaimsRedis getClaim(String id) {
        return claimsRepo.findById(id).orElse(null);
    }
}
