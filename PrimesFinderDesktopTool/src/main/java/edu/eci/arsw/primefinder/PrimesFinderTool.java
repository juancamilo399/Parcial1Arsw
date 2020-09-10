package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
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

	public static void main(String[] args) {

	    int maxPrim=54;
	    int numberOfThreads = 4;
	    PrimesResultSet prs=new PrimesResultSet("john");

        PrimeFinder[] threads = new PrimeFinder[numberOfThreads];

        int start=0;
        int end=0;
        int step=maxPrim/numberOfThreads;
        for (int i = 0; i <numberOfThreads ; i++) {
            end=start+step;
            if(i==0)end+=maxPrim%numberOfThreads;
            threads[i]=new PrimeFinder(new BigInteger(String.valueOf(start)) ,new BigInteger(String.valueOf(end)),prs);
            threads[i].start();
            start=end;
        }

        for(int i=0;i<numberOfThreads;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


	    System.out.println("Prime numbers found:");
	    System.out.println(prs.getPrimes());
            
	    /**
	    while(task_not_finished){
	        try {
	            //check every 10ms if the idle status (10 seconds without mouse
                //activity) was reached.
                Thread.sleep(10);
                if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                    System.out.println("Idle CPU ");
                }
                else{
                    System.out.println("User working again!");
                }
	        } catch (InterruptedException ex) {
	            Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
         */
	}
	
}


