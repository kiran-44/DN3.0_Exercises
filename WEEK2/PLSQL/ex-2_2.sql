ALTER TABLE Clients ADD VIPStatus BOOLEAN;

DECLARE
  CURSOR client_cursor IS
    SELECT ClientID
    FROM Clients
    WHERE CurrentBalance > 10000;
  
  client_ID Clients.ClientID%TYPE;
BEGIN
  OPEN client_cursor;
  
  LOOP
    FETCH client_cursor INTO client_ID;
    EXIT WHEN client_cursor%NOTFOUND;
    
    UPDATE Clients
    SET VIPStatus = TRUE
    WHERE ClientID = client_ID;
  END LOOP;
  
  CLOSE client_cursor;
END;
/
