CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE OpenAccount(
    p_AccountID IN NUMBER,
    p_CustomerID IN NUMBER,
    p_AccountType IN VARCHAR2,
    p_InitialBalance IN NUMBER
  );

  PROCEDURE CloseAccount(
    p_AccountID IN NUMBER
  );

  FUNCTION GetTotalBalance(
    p_CustomerID IN NUMBER
  ) RETURN NUMBER;
END AccountOperations;
/
CREATE OR REPLACE PACKAGE BODY AccountOperations AS

  PROCEDURE OpenAccount(
    p_AccountID IN NUMBER,
    p_CustomerID IN NUMBER,
    p_AccountType IN VARCHAR2,
    p_InitialBalance IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (p_AccountID, p_CustomerID, p_AccountType, p_InitialBalance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Account opened successfully: ' || p_AccountID);
  END OpenAccount;

  PROCEDURE CloseAccount(
    p_AccountID IN NUMBER
  ) IS
  BEGIN
    -- Delete related transactions
    DELETE FROM Transactions
    WHERE AccountID = p_AccountID;

    -- Delete the account
    DELETE FROM Accounts
    WHERE AccountID = p_AccountID;

    DBMS_OUTPUT.PUT_LINE('Account closed successfully: ' || p_AccountID);
  END CloseAccount;

  FUNCTION GetTotalBalance(
    p_CustomerID IN NUMBER
  ) RETURN NUMBER IS
    v_TotalBalance NUMBER;
  BEGIN
    SELECT SUM(Balance)
    INTO v_TotalBalance
    FROM Accounts
    WHERE CustomerID = p_CustomerID;

    RETURN v_TotalBalance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error calculating total balance.');
  END GetTotalBalance;

END AccountOperations;
/
BEGIN
  -- Test OpenAccount
  AccountOperations.OpenAccount(
    p_AccountID => 3, 
    p_CustomerID => 1, 
    p_AccountType => 'Checking', 
    p_InitialBalance => 2000
  );

  -- Test CloseAccount
  AccountOperations.CloseAccount(p_AccountID => 2);

  -- Test GetTotalBalance
  DECLARE
    v_TotalBalance NUMBER;
  BEGIN
    v_TotalBalance := AccountOperations.GetTotalBalance(1);
    DBMS_OUTPUT.PUT_LINE('Total balance for customer 1: ' || v_TotalBalance);
  END;
END;
/
