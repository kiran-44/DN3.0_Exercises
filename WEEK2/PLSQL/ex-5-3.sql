CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance Accounts.Balance%TYPE;
BEGIN
    -- Fetch the balance of the account
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = :NEW.AccountID;

    -- Check if the transaction is a withdrawal
    IF :NEW.TransactionType = 'Withdrawal' THEN
        -- Ensure the withdrawal amount does not exceed the balance
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance for withdrawal.');
        END IF;
    ELSIF :NEW.TransactionType = 'Deposit' THEN
        -- Ensure the deposit amount is positive
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    ELSE
        -- Handle invalid transaction types
        RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type. Must be "Deposit" or "Withdrawal".');
    END IF;
END;
/

BEGIN
  -- Enable DBMS_OUTPUT
  DBMS_OUTPUT.ENABLE;

  -- Test valid deposit
  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (3, 1, SYSDATE, 200, 'Deposit');
  DBMS_OUTPUT.PUT_LINE('Insert deposit successful for AccountID 1');

  -- Test valid withdrawal
  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (4, 2, SYSDATE, 1000, 'Withdrawal');
  DBMS_OUTPUT.PUT_LINE('Insert withdrawal successful for AccountID 2');

  -- Test invalid deposit (negative amount)
  BEGIN
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (5, 1, SYSDATE, -100, 'Deposit');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
  END;

  -- Test invalid withdrawal (amount exceeds balance)
  BEGIN
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (6, 1, SYSDATE, 600, 'Withdrawal');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
  END;

END;
/
