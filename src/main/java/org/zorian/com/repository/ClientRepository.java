package org.zorian.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zorian.com.entity.Client;
 
public interface ClientRepository extends JpaRepository<Client, Long> {
}
