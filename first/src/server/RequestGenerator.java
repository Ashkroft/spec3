package server;

import java.util.Random;

    public class RequestGenerator {
        private Server server;
        private Random gen = new Random();

    public RequestGenerator(Server server) {
        this.server = server;
    }

    //generate next request
    public Request nextRequest(){
        try {
            //wait some time to unexpected generating request
            Thread.sleep(Math.abs(gen.nextLong()) % 20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return new Request(Math.abs(gen.nextLong()) % 50, Math.abs(gen.nextInt()) % 1000,server);
    }
}
