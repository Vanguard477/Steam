package com.steam.domain.repository;

import com.steam.domain.entity.Item;
import com.steam.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    Optional<Item> findItemByUserAndClassId(User user, String classId);
}
