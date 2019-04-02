package com.cetin.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @NotNull
    private long id;

    @Column(name = "task_text")
    @NotNull
    private String task;

    @Column(name = "owner")
    private String owner;

    @Column(name = "is_completed")
    @NotNull
    private boolean isCompleted;

    public Task(@NotNull long id, @NotNull String task, String owner) {
        this.id = id;
        this.task = task;
        this.owner = owner;
        this.isCompleted=false;
    }

    public  Task(){}
}
