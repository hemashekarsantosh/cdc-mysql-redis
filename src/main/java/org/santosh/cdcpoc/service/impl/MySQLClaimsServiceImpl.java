package org.santosh.cdcpoc.service.impl;

import org.santosh.cdcpoc.domain.mysql.Claims;
import org.santosh.cdcpoc.repo.mysql.ClaimRepo;
import org.santosh.cdcpoc.service.MySqlClaimsService;
import org.springframework.stereotype.Service;

@Service
public class MySQLClaimsServiceImpl implements MySqlClaimsService {

    private ClaimRepo claimRepo;

    public MySQLClaimsServiceImpl(ClaimRepo claimRepo) {
        this.claimRepo = claimRepo;
    }

    @Override
    public Claims getClaims(Integer id) {
        return claimRepo.findById(id).orElse(null);
    }
}
