import java.util.*;


class SimpleDatabase {
  /*
   * Used a hashmap to store the key value pairs as well as the count of values
   * in the system. The trade off is added memory to achieve O(1) average time for
   * the SET, GET, UNSET, and NUMEQUALTO operations.
  */
  private  HashMap<String, Integer> database = new HashMap<>();
  private  HashMap<Integer, Integer> countMap = new HashMap<>();
  private  Node transactionStack;

  public void set(String key, Integer value){
    if(transactionStack != null){
      if(database.containsKey(key)){
          addTransactionToStack(key, database.get(key));
        }else{
          addTransactionToStack(key, null);
        }
      }
      database.put(key, value);
      if(countMap.containsKey(value)){
        countMap.put(value, countMap.get(value) + 1);
      }else{
      countMap.put(value, 1);
    }
  }

  public void unSet(String key){
    Integer temp = database.get(key);
    if(temp != null){
      if(transactionStack != null){
        addTransactionToStack(key, temp);
      }
      database.put(key, null);
      countMap.put(temp, countMap.get(temp) - 1);
    }
  }
  public void get(String key){
    System.out.println(key + ": " + database.get(key));
  }

  public void numEqualTo(int query){
    System.out.println(countMap.get(query));
  }

  public void beginTransaction(){
    Node temp = new Node("_BT", null);
    //System.out.println("Begining Stack");
    if(transactionStack == null){
      transactionStack = temp;
    }else{
      temp.next = transactionStack;
      transactionStack = temp;
    }
  }

  public void rollbackTransaction(){
    Node temp = transactionStack;
    //System.out.println("Rollback");
    if(temp != null){
      while(temp != null && !temp.key.equals("_BT")){
          //System.out.println(temp.key + ": " + temp.value);
          unSet(temp.key);
          set(temp.key, temp.value);
          temp = temp.next;
      }
      transactionStack = temp.next;
    }else{
      System.out.println("NO TRANSACTION");
    }
  }
  public void commitTransaction(){
    transactionStack = null;
  }

  public void printHelp(){
    System.out.println("SET - 'SET key value' Where key is a string and value and integer.");
    System.out.println("GET - 'GET key' Where key is a string. Returns null if key not found.");
    System.out.println("UNSET - 'UNSET key value' Where key is a string. Sets key value to null.");
    System.out.println("NUMEQUALTO - 'NUMEQUALTO value' Where value and integer. Returns number of times value appears in database.");
    System.out.println("BEGIN - 'SET key value' Begins transaction. Can be nested.");
    System.out.println("ROLLBACK - 'ROLLBACK' Reverts all changes to last call to BEGIN.");
    System.out.println("COMMIT - 'COMMIT' Makes all pending transactions permanent.");

  }

  private void addTransactionToStack(String key, Integer value){
    Node temp = new Node(key, value);
    temp.next = transactionStack;
    transactionStack = temp;
  }

  private class Node{
    String key;
    Integer value;
    Node next;

    public Node(String key, Integer value){
      this.key = key;
      this.value = value;
    }

  }
}
