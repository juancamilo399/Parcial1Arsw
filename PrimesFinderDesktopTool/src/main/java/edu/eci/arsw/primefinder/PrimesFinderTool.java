package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

    public static boolean pause;
    public static  Object monitor=new Object();

    public static void main(String[] args) {

	    int maxPrim=54;
	    int numberOfThreads = 4;

	    PrimesResultSet prs=new PrimesResultSet("john");

        PrimeFinder[] threads = new PrimeFinder[numberOfThreads];

        int start=0;
        int end;
        int step=maxPrim/numberOfThreads;
        for (int i = 0; i <numberOfThreads ; i++) {
            end=start+step;
            if(i==0)end+=maxPrim%numberOfThreads;
            threads[i]=new PrimeFinder(new BigInteger(String.valueOf(start)) ,new BigInteger(String.valueOf(end)),prs);
            threads[i].start();
            start=end;
        }


        while (Arrays.stream(threads).anyMatch(Thread::isAlive)) {
            if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() < 10000) {
                System.out.println("pause");
                pause = true;
            } else {
                pause = false;
                synchronized (monitor){
                    monitor.notifyAll();
                }
            }
        }


        System.out.println("Prime numbers found:");
        System.out.println(prs.getPrimes());
	}
	
}


