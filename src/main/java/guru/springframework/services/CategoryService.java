package guru.springframework.services;

import guru.springframework.domain.Category;

public interface CategoryService {

    Iterable<Category> listAllCategorys();

    Category getCategoryById(Integer id);

    Category saveCategory(Category category);

    void deleteCategory(Integer id);
}
