package org.santosh.cdcpoc.domain.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "claims")
@Data
public class Claims {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ClaimId")
    private Integer id;

    @Column(name = "Type")
    private String type;
    @Column(name = "Amount")
    private String amount;

}
