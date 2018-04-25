/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArrayHashTable;

/**
 *
 * @author Ranjith
 */
public class ArrayHashTable {

    private Employee[] hashTable;

    public ArrayHashTable() {
        hashTable = new Employee[10];
    }

    //while inserting the elements we put using the key
    //key here is done using the last name of the employee
    public void put(String key, Employee employeeValue) {

        //get a hash ket value based on lastName of the employee
        int hashedKey = hashedString(key);

        //if an element exists at the key position
        if (occupied(hashedKey)) {
            int probeValue = linearProbing(hashedKey);
//            System.out.println("probeValue = " + probeValue);
            if (probeValue == -1) {
                //no available index to put
                System.out.println("No available slot to add");
            } else {
                //insert at the nextAvailableIndex
                hashTable[probeValue] = employeeValue;
                employeeValue.setKey(key);
            }
        } else {
            hashTable[hashedKey] = employeeValue;
            employeeValue.setKey(key);
        }
    }

    private int linearProbing(int hashedKey) {
        //collision avoidance - linearKey
        //check till the end of the hashTable
        /*
        use only one loop to check till the end of hashtable and then wrap
         */
        for (int loopIndex = hashedKey + 1; loopIndex < hashTable.length; loopIndex++) {
            if (!occupied(loopIndex)) {
                return loopIndex;
            }
        }
        //wrap around
        for (int loopIndex = 0; loopIndex < hashedKey; loopIndex++) {
            if (!occupied(loopIndex)) {
                return loopIndex;
            }
        }
        //no available position
        return -1;
    }

    private boolean occupied(int hashedKey) {
        boolean isOccupied = hashTable[hashedKey] != null;
        return isOccupied;
    }

//    public void checkAll(){
//        for(int i =0; i < hashTable.length; i++){
//            if(hashTable[i] == null){
//                System.out.println("Printing null..."+i);
//            }else{
//                System.out.println("value present "+hashTable[i]);
//            }
//        }
//    }
    //complexity - constant time :)
    public Employee get(String key) {
        //get hashed value of the key
        int hashedKey = hashedString(key);

        //if no element exists
        if (hashTable[hashedKey] == null) {
            return null;
        }

        //how do we get an element which was put through linear probing?
        if (hashTable[hashedKey].getKey().equals(key)) {
            //matching key found
            Employee returnValue = hashTable[hashedKey];
            return returnValue;
        } else {
            //matching ket not found 
            //do linear probing
            int stopIndex = hashedKey;
            for (int loopIndex = stopIndex + 1; loopIndex < hashTable.length; loopIndex++) {
                if (hashTable[loopIndex].getKey().equals(key)) {
                    Employee returnValue = hashTable[loopIndex];
                    return returnValue;
                }
            }
            //wrap around
            for (int loopIndex = 0; loopIndex < stopIndex; loopIndex++) {
                if (hashTable[loopIndex].getKey().equals(key)) {
                    Employee returnValue = hashTable[loopIndex];
                    return returnValue;
                }
            }

        }
        return null;
    }

    //to get the indices in the range of 0-9
    //since hashtable's length is 10
    private int hashedString(String key) {
        return key.length() % hashTable.length;
    }

    public void printHashTable() {
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println(i + "-->" + hashTable[i]);
        }
    }

}
