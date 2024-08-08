CREATE OR REPLACE PACKAGE CustomerManagement AS
  PROCEDURE AddCustomer(
    p_CustomerID IN NUMBER,
    p_Name IN VARCHAR2,
    p_DOB IN DATE,
    p_Balance IN NUMBER
  );

  PROCEDURE UpdateCustomerDetails(
    p_CustomerID IN NUMBER,
    p_Name IN VARCHAR2,
    p_DOB IN DATE
  );

  FUNCTION GetCustomerBalance(
    p_CustomerID IN NUMBER
  ) RETURN NUMBER;
END CustomerManagement;
/
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

  PROCEDURE AddCustomer(
    p_CustomerID IN NUMBER,
    p_Name IN VARCHAR2,
    p_DOB IN DATE,
    p_Balance IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);

    DBMS_OUTPUT.PUT_LINE('Customer added successfully: ' || p_CustomerID);
  END AddCustomer;

  PROCEDURE UpdateCustomerDetails(
    p_CustomerID IN NUMBER,
    p_Name IN VARCHAR2,
    p_DOB IN DATE
  ) IS
  BEGIN
    UPDATE Customers
    SET Name = p_Name,
        DOB = p_DOB,
        LastModified = SYSDATE
    WHERE CustomerID = p_CustomerID;

    DBMS_OUTPUT.PUT_LINE('Customer details updated successfully: ' || p_CustomerID);
  END UpdateCustomerDetails;

  FUNCTION GetCustomerBalance(
    p_CustomerID IN NUMBER
  ) RETURN NUMBER IS
    v_Balance NUMBER;
  BEGIN
    SELECT Balance
    INTO v_Balance
    FROM Customers
    WHERE CustomerID = p_CustomerID;

    RETURN v_Balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error retrieving customer balance.');
  END GetCustomerBalance;

END CustomerManagement;
/
BEGIN
  -- Test AddCustomer
  CustomerManagement.AddCustomer(3, 'Michael Johnson', TO_DATE('1978-11-25', 'YYYY-MM-DD'), 2000);

  -- Test UpdateCustomerDetails
  CustomerManagement.UpdateCustomerDetails(1, 'John Doe Jr.', TO_DATE('1985-05-15', 'YYYY-MM-DD'));

  -- Test GetCustomerBalance
  DECLARE
    v_Balance NUMBER;
  BEGIN
    v_Balance := CustomerManagement.GetCustomerBalance(1);
    DBMS_OUTPUT.PUT_LINE('Customer balance: ' || v_Balance);
  END;
END;
/
