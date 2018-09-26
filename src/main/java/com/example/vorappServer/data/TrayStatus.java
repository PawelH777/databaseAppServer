package com.example.vorappServer.data;

public enum TrayStatus {
    WAITING,
    IN_PROGRESS,
    FINISHED,
    ABANDONED;

    private TrayStatus nextStatus;

    static {
        WAITING.nextStatus = IN_PROGRESS;
        IN_PROGRESS.nextStatus = FINISHED;
        FINISHED.nextStatus = ABANDONED;
        ABANDONED.nextStatus = WAITING;
    }

    public TrayStatus getNextStatus(){
        return nextStatus;
    }
}
