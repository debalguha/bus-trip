package org.progressivelifestyle.bustrip.google.repository;

import org.progressivelifestyle.bustrip.google.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long>{
	public Category findByCategory(String category);
}
