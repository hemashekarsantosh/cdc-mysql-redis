package org.santosh.cdcpoc.controller;

import org.santosh.cdcpoc.domain.mysql.Claims;
import org.santosh.cdcpoc.service.MySqlClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySqlController {

    private MySqlClaimsService mySqlClaimsService;

    @Autowired
    public MySqlController(MySqlClaimsService mySqlClaimsService) {
        this.mySqlClaimsService = mySqlClaimsService;
    }

    @GetMapping("/mysql/claims/{id}")
    public Claims claims(@PathVariable Integer id) {
        return mySqlClaimsService.getClaims(id);
    }
}
