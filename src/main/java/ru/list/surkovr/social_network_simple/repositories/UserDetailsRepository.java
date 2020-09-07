package ru.list.surkovr.social_network_simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.list.surkovr.social_network_simple.domain.User;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
