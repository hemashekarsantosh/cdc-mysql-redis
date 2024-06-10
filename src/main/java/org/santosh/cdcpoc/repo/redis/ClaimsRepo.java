package org.santosh.cdcpoc.repo.redis;

import org.santosh.cdcpoc.domain.redis.ClaimsRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepo extends CrudRepository<ClaimsRedis,String> {

}
