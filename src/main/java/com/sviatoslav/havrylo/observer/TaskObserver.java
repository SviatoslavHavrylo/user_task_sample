package com.sviatoslav.havrylo.observer;

import com.sviatoslav.havrylo.service.ITaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@RequiredArgsConstructor
@Component
@Slf4j
public class TaskObserver {

    private final ITaskService service;

    @Scheduled(fixedDelay = 60000)
    public void updateTaskStatus() {
        log.info("update Task Status");
        service.updateTaskStatus();
    }
}
