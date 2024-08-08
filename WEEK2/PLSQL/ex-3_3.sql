CREATE OR REPLACE PROCEDURE TransferBalance(
    senderAccount IN INT,
    recipientAccount IN INT,
    transferAmount IN INT
) 
AS
    availableFunds NUMBER;
BEGIN
    SELECT CurrentBalance INTO availableFunds 
    FROM BankingAccounts 
    WHERE AccountNumber = senderAccount 
    FOR UPDATE;
    
    IF transferAmount > availableFunds THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient Balance');
    END IF;
   
    UPDATE BankingAccounts 
    SET CurrentBalance = CurrentBalance - transferAmount, LastUpdate = SYSDATE
    WHERE AccountNumber = senderAccount;
    
    UPDATE BankingAccounts 
    SET CurrentBalance = CurrentBalance + transferAmount, LastUpdate = SYSDATE
    WHERE AccountNumber = recipientAccount;
  
    COMMIT;
END;
/
DECLARE
    CURSOR account_cursor IS
        SELECT AccountNumber, CurrentBalance, LastUpdate
        FROM BankingAccounts
        WHERE AccountNumber IN (1, 2); 
    account_record account_cursor%ROWTYPE;
BEGIN
    TransferBalance(1, 2, 500);

    OPEN account_cursor;
    
    LOOP
        FETCH account_cursor INTO account_record;
        EXIT WHEN account_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('AccountNumber: ' || account_record.AccountNumber || 
                             ', CurrentBalance: ' || account_record.CurrentBalance ||
                             ', LastUpdate: ' || account_record.LastUpdate);
    END LOOP;
    
    CLOSE account_cursor;
END;
/
