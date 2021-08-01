package com.event.app.repository;

import com.event.app.bean.EventObject;
import com.event.app.bean.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventObjectRepository extends JpaRepository<EventObject, Long> {

  List<EventObject> findByUser(User user);
}

