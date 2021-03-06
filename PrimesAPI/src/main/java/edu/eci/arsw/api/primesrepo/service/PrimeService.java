package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.PrimeException;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.List;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public interface PrimeService
{

    void addFoundPrime( FoundPrime foundPrime ) throws PrimeException;

    List<FoundPrime> getFoundPrimes() throws PrimeException;

    FoundPrime getPrime( String prime ) throws PrimeException;

}
