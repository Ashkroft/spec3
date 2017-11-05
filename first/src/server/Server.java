package server;

import util.Util;

import java.io.*;
import java.util.Date;


public class Server implements Runnable {
    private int resources;
    private long endTime;
    private long currentTime;
    private FileWriter protocol;//file in which to write the log
    private int executableRequests;

    //initialization
    public Server(String configFileName) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(configFileName));
        } catch (IOException e) {
            e.printStackTrace();
            in = new BufferedReader(new InputStreamReader(System.in));
        }
        try {
            //directly forming protocol
            protocol = new FileWriter(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            protocol = new FileWriter(FileDescriptor.out);
        }
        try {
            resources = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            resources = 0;
        }
        try {
            endTime = Long.parseLong(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            endTime = 0;
        }
    }

    //allocating
    private synchronized boolean getResource(int size) {
        //check on availability allocation
        if (resources >= size) {
            //allocate and print to log
            resources = resources - size;
            Util.printGetSuccessful(new Date(currentTime), resources, protocol);
            return true;
        }
        //report about error
        Util.printGetFailed(new Date(currentTime), resources, protocol);
        return false;
    }

    public synchronized void releaseResource(int size) {
        resources = resources + size;
        Util.printRelease(new Date(currentTime), resources, protocol);
        executableRequests--;
    }

    @Override
    public void run() {
        RequestGenerator requestGenerator = new RequestGenerator(this);
        executableRequests = 0;
        currentTime = System.currentTimeMillis();
        endTime += currentTime;
        Request request;
        //cycle which work while have time
        while (endTime > currentTime) {
            request = requestGenerator.nextRequest();
            if (getResource(request.getResource())) {
                new Thread(request).start();
                executableRequests++;
            }
            currentTime = System.currentTimeMillis();
        }
        //wait returning all resources
        while (executableRequests != 0) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //close log stream
        try{
            protocol.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
