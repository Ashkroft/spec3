package server;

import util.Util;


public class Request implements Runnable {
    private long workTime;
    private int resource;
    private Server server;

    public int getResource() {
        return resource;
    }

    public Request(long workTime, int resource, Server server) {
        this.workTime = workTime;
        this.resource = resource;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(workTime);
        }catch (InterruptedException e){
            e.printStackTrace();//output exceptions
        }
        server.releaseResource(resource);
    }
}
