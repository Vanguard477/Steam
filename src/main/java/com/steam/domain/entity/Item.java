package com.steam.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Description> descriptions;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String instanceId;
    private String classId;
    private String iconUrl;
    private String name;
    private String marketName;



}


