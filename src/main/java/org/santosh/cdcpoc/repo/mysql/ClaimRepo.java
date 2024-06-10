package org.santosh.cdcpoc.repo.mysql;

import org.santosh.cdcpoc.domain.mysql.Claims;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepo extends CrudRepository<Claims, Integer> {


}
