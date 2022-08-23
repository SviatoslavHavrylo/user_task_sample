package com.sviatoslav.havrylo.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum TaskStatus {
    PENDING("pending"),
    DONE("done");

    @JsonValue
    private final String name;

    TaskStatus(final String name) {
        this.name = name;
    }

    public static TaskStatus valueOfName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        for (TaskStatus status : values()) {
            if (StringUtils.equals(status.getName(), name)) {
                return status;
            }
        }
        return null;
    }
}
