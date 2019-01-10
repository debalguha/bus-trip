package org.progressivelifestyle.bustrip.google.repository;

import org.progressivelifestyle.bustrip.consumer.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository<T extends Consumer>  extends JpaRepository<T, Long> {
	public T findByEmailId(String emailId);
}
