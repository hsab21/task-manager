package com.example.task_manager.configuration;

import com.example.task_manager.infrastructure.TaskRepository;
import com.example.task_manager.infrastructure.InMemoryTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RepositoryConfig {

    @Bean
    @Primary
    public TaskRepository taskRepository() {
        return new InMemoryTaskRepository();
    }
}
