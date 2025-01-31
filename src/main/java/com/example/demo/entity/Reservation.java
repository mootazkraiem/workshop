package com.example.demo.entity;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private int userId;
    private int TicketId;
    private String status;

    public Reservation() {
    }

    public Reservation(int id, LocalDateTime startTime, LocalDateTime endTime, int userId, int TicketId, String status) {
        this.id = id;
        StartTime = startTime;
        EndTime = endTime;
        this.userId = userId;
        this.TicketId = TicketId;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        StartTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        EndTime = endTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int TicketId) {
        this.TicketId = TicketId;
    }
}
