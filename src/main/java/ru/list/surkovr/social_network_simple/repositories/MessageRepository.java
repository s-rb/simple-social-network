package ru.list.surkovr.social_network_simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.list.surkovr.social_network_simple.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
