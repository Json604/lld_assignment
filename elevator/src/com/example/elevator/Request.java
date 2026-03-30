package com.example.elevator;

public class Request {
    private final int targetFloor;
    private final Direction dir;

    public Request(int targetFloor, Direction dir) {
        this.targetFloor = targetFloor;
        this.dir = dir;
    }

    public int getFloor()           { return targetFloor; }
    public Direction getDirection()  { return dir; }

    @Override
    public String toString() {
        return "Req[floor=" + targetFloor + ", dir=" + dir + "]";
    }
}
