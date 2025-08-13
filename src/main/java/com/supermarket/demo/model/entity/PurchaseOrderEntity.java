package com.supermarket.demo.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "purchase_order")
@Getter
@Setter
public class PurchaseOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ship_to", length = 100, nullable = false)
    private String shipTo;

    @Column(name = "supermarket_name", length = 30, nullable = false)
    private String supermarketName;

    @Column(name = "payment_method", length = 10, nullable = false)
    private String paymentMethod;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonBackReference
    private SupplierEntity supplier;

    @OneToMany(mappedBy = "purchaseOrder", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PurchaseOrderDetailEntity> purchaseOrderDetails;
}
