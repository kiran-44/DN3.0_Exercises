BEGIN
  -- Create Customers Table
  EXECUTE IMMEDIATE 'CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
  )';

  -- Create Accounts Table
  EXECUTE IMMEDIATE 'CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
  )';

  -- Create Transactions Table
  EXECUTE IMMEDIATE 'CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
  )';

  -- Create Loans Table
  EXECUTE IMMEDIATE 'CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
  )';

  -- Create Employees Table
  EXECUTE IMMEDIATE 'CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
  )';
END;
/

BEGIN
  -- Insert into Customers
  INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
  VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

  INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
  VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

  -- Insert into Accounts
  INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
  VALUES (1, 1, 'Savings', 1000, SYSDATE-30);

  INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
  VALUES (2, 2, 'Savings', 1500, SYSDATE-30);

  -- Insert into Transactions
  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (1, 1, SYSDATE, 200, 'Deposit');

  INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
  VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

  -- Insert into Loans
  INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
  VALUES (1, 1, 5000, 5, SYSDATE, SYSDATE+25);

  -- Insert into Employees
  INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
  VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

  INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
  VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
END;
/

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
  -- Cursor to fetch the accounts that will be updated
  CURSOR accounts_cursor IS
    SELECT AccountID, CustomerID, Balance
    FROM Accounts
    WHERE AccountType = 'Savings'
      AND (SYSDATE - LastModified) >= 30
      FOR UPDATE;
  
  -- Variables to hold the account details
  v_account_id Accounts.AccountID%TYPE;
  v_customer_id Accounts.CustomerID%TYPE;
  v_balance Accounts.Balance%TYPE;
BEGIN
  -- Open the cursor
  OPEN accounts_cursor;
  
  -- Process each row
  LOOP
    FETCH accounts_cursor INTO v_account_id, v_customer_id, v_balance;
    EXIT WHEN accounts_cursor%NOTFOUND;
    
    -- Update the balance and LastModified date
    UPDATE Accounts
    SET Balance = v_balance * 1.01,
        LastModified = SYSDATE
    WHERE AccountID = v_account_id;
    
    -- Output the updated details
    DBMS_OUTPUT.PUT_LINE('AccountID: ' || v_account_id || 
                         ', CustomerID: ' || v_customer_id || 
                         ', New Balance: ' || v_balance * 1.01);
  END LOOP;
  
  -- Close the cursor
  CLOSE accounts_cursor;
  
  -- Commit the transaction
  COMMIT;
  
  DBMS_OUTPUT.PUT_LINE('Monthly interest of 1% applied to all eligible savings accounts.');
END;
/
BEGIN
  ProcessMonthlyInterest;
END;
/




