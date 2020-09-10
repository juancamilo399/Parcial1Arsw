package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */

@RequestMapping(value = "/primes")
@RestController
public class PrimesController
{

    @Autowired
    @Qualifier("Services")
    PrimeService primeService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getPrimes() {
        try {
            return new ResponseEntity<>(primeService.getFoundPrimes(), HttpStatus.ACCEPTED);
        } catch (PrimeException e) {
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{primenumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getPrime(@PathVariable String primenumber) {
        try {
            return new ResponseEntity<>(primeService.getPrime(primenumber), HttpStatus.ACCEPTED);
        } catch (PrimeException e) {
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPrime(@RequestBody FoundPrime primenumber) {
        try {
            primeService.addFoundPrime(primenumber);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (PrimeException e) {
            Logger.getLogger(PrimesController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }







}
