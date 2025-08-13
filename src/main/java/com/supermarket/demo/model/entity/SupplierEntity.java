package com.supermarket.demo.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "contact_number", length = 20, nullable = false)
    private String contactNumber;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @Column(name = "contract_start_date", nullable = false)
    private LocalDateTime contractStartDate;

    @Column(name = "contract_end_date", nullable = false)
    private LocalDateTime contractEndDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PurchaseOrderDetailEntity> purchaseOrderDetails;
}
