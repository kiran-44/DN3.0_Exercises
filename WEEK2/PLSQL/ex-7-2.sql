CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireEmployee(
    p_EmployeeID IN NUMBER,
    p_Name IN VARCHAR2,
    p_Position IN VARCHAR2,
    p_Salary IN NUMBER,
    p_Department IN VARCHAR2,
    p_HireDate IN DATE
  );

  PROCEDURE UpdateEmployeeDetails(
    p_EmployeeID IN NUMBER,
    p_Name IN VARCHAR2,
    p_Position IN VARCHAR2,
    p_Salary IN NUMBER,
    p_Department IN VARCHAR2
  );

  FUNCTION CalculateAnnualSalary(
    p_EmployeeID IN NUMBER
  ) RETURN NUMBER;
END EmployeeManagement;
/
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

  PROCEDURE HireEmployee(
    p_EmployeeID IN NUMBER,
    p_Name IN VARCHAR2,
    p_Position IN VARCHAR2,
    p_Salary IN NUMBER,
    p_Department IN VARCHAR2,
    p_HireDate IN DATE
  ) IS
  BEGIN
    INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
    VALUES (p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, p_HireDate);

    DBMS_OUTPUT.PUT_LINE('Employee hired successfully: ' || p_EmployeeID);
  END HireEmployee;

  PROCEDURE UpdateEmployeeDetails(
    p_EmployeeID IN NUMBER,
    p_Name IN VARCHAR2,
    p_Position IN VARCHAR2,
    p_Salary IN NUMBER,
    p_Department IN VARCHAR2
  ) IS
  BEGIN
    UPDATE Employees
    SET Name = p_Name,
        Position = p_Position,
        Salary = p_Salary,
        Department = p_Department,
        HireDate = HireDate  -- Assuming HireDate doesn't change on update
    WHERE EmployeeID = p_EmployeeID;

    DBMS_OUTPUT.PUT_LINE('Employee details updated successfully: ' || p_EmployeeID);
  END UpdateEmployeeDetails;

  FUNCTION CalculateAnnualSalary(
    p_EmployeeID IN NUMBER
  ) RETURN NUMBER IS
    v_Salary NUMBER;
  BEGIN
    SELECT Salary
    INTO v_Salary
    FROM Employees
    WHERE EmployeeID = p_EmployeeID;

    RETURN v_Salary * 12;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error calculating annual salary.');
  END CalculateAnnualSalary;

END EmployeeManagement;
/
BEGIN
  -- Test HireEmployee
  EmployeeManagement.HireEmployee(
    p_EmployeeID => 3, 
    p_Name => 'Sarah Connor', 
    p_Position => 'Analyst', 
    p_Salary => 5000, 
    p_Department => 'Finance', 
    p_HireDate => TO_DATE('2023-01-15', 'YYYY-MM-DD')
  );

  -- Test UpdateEmployeeDetails
  EmployeeManagement.UpdateEmployeeDetails(
    p_EmployeeID => 1, 
    p_Name => 'Alice Johnson', 
    p_Position => 'Senior Manager', 
    p_Salary => 80000, 
    p_Department => 'HR'
  );

  -- Test CalculateAnnualSalary
  DECLARE
    v_AnnualSalary NUMBER;
  BEGIN
    v_AnnualSalary := EmployeeManagement.CalculateAnnualSalary(1);
    DBMS_OUTPUT.PUT_LINE('Annual salary: ' || v_AnnualSalary);
  END;
END;
/