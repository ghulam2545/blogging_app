package com.ghulam.repositories;

import com.ghulam.models.Category;
import com.ghulam.models.Post;
import com.ghulam.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
}
