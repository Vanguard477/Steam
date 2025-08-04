package com.steam.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "DESCRIPTIONS")

public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
    @Column(columnDefinition = "text")
    private String type;
    @Column(columnDefinition="text")
    private String value;
    @Column(columnDefinition="text")
    private String name;
}
