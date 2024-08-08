
CREATE OR REPLACE TRIGGER UpdateClientLastModified
BEFORE UPDATE ON Clients
FOR EACH ROW 
BEGIN
    :NEW.LastUpdate := SYSDATE;
END;
/
DECLARE
    CURSOR client_cursor IS
        SELECT ClientID, FullName, DateOfBirth, CurrentBalance, LastUpdate
        FROM Clients;

    client_record client_cursor%ROWTYPE;
BEGIN
    OPEN client_cursor;
    
    LOOP
        FETCH client_cursor INTO client_record;
        EXIT WHEN client_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('ClientID: ' || client_record.ClientID || 
                             ', FullName: ' || client_record.FullName || 
                             ', DateOfBirth: ' || client_record.DateOfBirth || 
                             ', CurrentBalance: ' || client_record.CurrentBalance || 
                             ', LastUpdate: ' || client_record.LastUpdate);
    END LOOP;
    
    CLOSE client_cursor;
END;
/
