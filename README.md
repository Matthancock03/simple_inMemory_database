#Simple In Memory Database. 


<strong>Commands:</strong><br>
  SET - 'SET key value' Where key is a string and value and integer.<br>
  GET - 'GET key' Where key is a string. Returns null if key not found.<br>
  UNSET - 'UNSET key value' Where key is a string. Sets key value to null.<br>
  NUMEQUALTO - 'NUMEQUALTO value' Where value and integer. Returns number of times value appears in database.<br>
  BEGIN - 'SET key value' Begins transaction. Can be nested."<br>
  ROLLBACK - 'ROLLBACK' Reverts all changes to last call to BEGIN.<br>
  COMMIT - 'COMMIT' Makes all pending transactions permanent.<br>
