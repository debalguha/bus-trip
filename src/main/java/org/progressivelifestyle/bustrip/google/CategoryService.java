package org.progressivelifestyle.bustrip.google;

import java.util.List;
import java.util.Map;

import org.progressivelifestyle.bustrip.google.domain.Category;
import org.progressivelifestyle.bustrip.google.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Maps;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo catRepo;
	
	@Transactional
	public Map<String, Category> findAll() throws Exception {
		List<Category> all = catRepo.findAll();
		Map<String, Category> categoryMap = Maps.newHashMap();
		for(Category category : all)
			categoryMap.put(category.getCategory(), category);
		return categoryMap;
	}
}
