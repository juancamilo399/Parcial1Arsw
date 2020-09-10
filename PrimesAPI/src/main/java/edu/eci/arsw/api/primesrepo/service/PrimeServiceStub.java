package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.PrimeException;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */

@Service("Services")
public class PrimeServiceStub implements PrimeService {

    private List<FoundPrime> primes = new CopyOnWriteArrayList<>();

    public PrimeServiceStub() {
        primes.add(new FoundPrime("juan", "3"));
        primes.add(new FoundPrime("juan", "5"));
        primes.add(new FoundPrime("juan", "19"));
    }


    @Override
    public void addFoundPrime( FoundPrime foundPrime ) throws PrimeException {

        boolean ans= primes.stream().
                anyMatch(p->p.getPrime().equals(foundPrime.getPrime()));

        if(ans) throw new PrimeException("Este numero primo ya existe");
        primes.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes() throws PrimeException {
        return primes;
    }

    @Override
    public FoundPrime getPrime( String prime ) throws PrimeException {
        FoundPrime primo = primes.stream()
                .filter(p -> p.getPrime().equals(prime))
                .findFirst()
                .orElse(null);

        if(primo!=null) return primo;
        throw new PrimeException("Este numero primo no existe");
    }
}

