package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinder extends Thread{

    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;

    public PrimeFinder(BigInteger a, BigInteger b, PrimesResultSet prs) {
        this.a = a;
        this.b = b;
        this.prs = prs;
    }

    @Override
    public void run() {
        MathUtilities mt = new MathUtilities();
        BigInteger i = a;
        while (i.compareTo(b) <= 0) {
            synchronized (PrimesFinderTool.monitor) {
                if (PrimesFinderTool.pause) {
                    try {
                        PrimesFinderTool.monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (mt.isPrime(i)) {
                prs.addPrime(i);
            }
            i = i.add(BigInteger.ONE);
        }
    }
}
