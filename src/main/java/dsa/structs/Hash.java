package dsa.structs;
import dsa.data.Patient;

/**
 * This class represents a single patient record
 * 
 */
public class Hash {
//    DSALinkedList<String>[] hashArray;
    Patient[] hashArray; // hashArray stores the key, values 
    
    /**
    * Hash Constructor
    * @param  length, the value to find the next prime after.
    **/
    public Hash(int length) {
        hashArray = new Patient[ findNextPrime(length) ];
    }

    /**
     * get Internal Hash Array
     * @return Patient[] Array
     */
    public Patient[] getHashArray() {
        return hashArray;
    }
    
    /**
     * add a record to the hash array using the id as the key
     * @param record 
     */
    public void put(Patient record) {
        int key = record.getId();
        int index = firstHash(key);
        int step = secondHash(key);

        // Resize if load factor exceeds 50%
        if (load() >= (hashArray.length / 2)) {
//            System.out.println("*********resizing**********");
            resize(hashArray.length * 4); // 4x the size of array
            
            // After resize, re-calculate indices since array length changed
            index = firstHash(key);
            step = secondHash(key);
        }

        // check collision
        // Probe until we find an empty slot or a slot with the same key
        while (hashArray[index] != null) {
            // Optional: update existing record if same key found
            if (hashArray[index].getId() == key) {
                hashArray[index] = record;
                return;
            }
            index = (index + step) % hashArray.length;
        }

        // Insert into the found empty slot
        hashArray[index] = record;
    }

    
    /**\
     *  get patient record
     * @param id to use for key
     * @return Patient record for id number
     */
    public Patient get(int id) {
        int index = firstHash(id);
        int step = secondHash(id);

        // Probe sequence (double hashing)
        while (hashArray[index] != null) {
            if (hashArray[index].getId() == id) {
                return hashArray[index]; // found
            }
            index = (index + step) % hashArray.length; // move to next candidate
        }

        return null; // not found
    }
    
    /**
    * resize the hash array
    * @param  size, new size
    **/    
    public void resize(int size) {
        Patient[] oldArray = hashArray;
        int oldLength = oldArray.length; // need to store old length and update to new for secondHash
        
        // create new array
        hashArray = new Patient[ findNextPrime( size ) ];
        
        // copy old array to new array
        for (int i = 0; i < oldLength; i++) {
            if (oldArray[i] != null) {
                Patient data = oldArray[i];     // copy this patient
                int key = data.getId();         // get the id for rehashing
                put(data);                      // rehash usng put so that double hashing happens
            }
        }
    }
    
    /**
    * return a hashed index based on the id
    * first hash uses a multiplicative
    * @param  key, the value to hash
    * @return hash = a multiplicative fraction multiple
    **/
    private int firstHash(int id) {
        double A = 0.6180339887; // Knuth's multiplicative constant
        double frac = (id * A) % 1;
        return (int)(frac * hashArray.length);
    }

    /**
    * return a hashed index based on the id
    * second hash uses a simple mod
    * @param  key, the value to hash
    * @return Hash = the modulo of the absolute value
    **/
    private int secondHash(int key) {
        int A = Math.abs(key % hashArray.length);
        if( A == 0 ) A = 1; // because used as step 0 will cause inf loop
        return A;
    }
    
    /**
    * setting the length to a prime helps distribute the keys avoiding clustering
    * @param  start, the value to find the next prime after.
    * @return the number of non null entries in the array
    * @throws TypeException if start is not a Integer
    **/
    private static int findNextPrime(int start) {
        int prime;

        if( start % 2 == 0) prime = start - 1; //change to odd
        else prime = start - 2;
        
        Boolean isPrime = false;
        
        while(!isPrime) {
            prime += 2; // next candidate
            int ii = 3;
            isPrime = true;
            
            while( ii * ii <= prime && isPrime) {
                if(prime % ii == 0) isPrime = false;
                else ii += 2;
            }
        }
        return prime;
    }
    
    /**
    * Find the current load of the array
    * @param  none
    * @return the number of non null entries in the array
    * @throws ArithmeticException if b is zero
    **/
    private int load() {
        int n = 0;
        
        for(int i=0; i<hashArray.length; i++)
            if(hashArray[i] != null) n++;
        
        return n;
    }
    
////// need to actually find the record //////
    public void remove(int key) {
        hashArray[ secondHash(key) ] = null;
    }
    
////// needs to be updated with double hashing
    public Boolean hasKey(String key) {
        Boolean found = false;
        
        // check the primary key location !null: still need to check further since an item could be deleted.
        // check if the primary key loc has the key: return true
        // else check the next x secondary hash locations
        
//        if( hashArray[ secondHash(key) ] != null ) {
//            if( hashArray[ secondHash(key) ] == key) found = true;
//            if( hashArray[ secondaryHashFunction( key ) ] == key]) found = true;
//        }
        
        return found;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < hashArray.length; i++) {
            if( hashArray[i] != null )
                builder.append(i).append(":").append(hashArray[i]);
            else
                builder.append(i).append(":").append("empty\n");
        }
        return builder.toString();
    }
    
}
