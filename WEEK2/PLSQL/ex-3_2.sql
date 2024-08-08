
CREATE OR REPLACE PROCEDURE AdjustStaffBonus(
    bonusPercentage IN INT,
    department IN VARCHAR2
) 
AS
BEGIN
    UPDATE Staff
    SET AnnualSalary = AnnualSalary + (AnnualSalary * (bonusPercentage / 100))
    WHERE Division = department;

    COMMIT;
END;
/

BEGIN
    AdjustStaffBonus(10, 'IT');
END;
/
DECLARE
    CURSOR staff_cursor IS
        SELECT StaffID, FullName, JobTitle, AnnualSalary, Division, DateOfHire
        FROM Staff
        WHERE Division = 'IT'; -- Replace 'IT' with the department you want to check

    staff_record staff_cursor%ROWTYPE;
BEGIN
    -- Call the procedure
    AdjustStaffBonus(10, 'IT');

    -- Open the cursor
    OPEN staff_cursor;
    
    -- Loop through the cursor and display the updated data
    LOOP
        FETCH staff_cursor INTO staff_record;
        EXIT WHEN staff_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('StaffID: ' || staff_record.StaffID || 
                             ', FullName: ' || staff_record.FullName || 
                             ', JobTitle: ' || staff_record.JobTitle || 
                             ', AnnualSalary: ' || staff_record.AnnualSalary || 
                             ', Division: ' || staff_record.Division || 
                             ', DateOfHire: ' || staff_record.DateOfHire);
    END LOOP;
    
    -- Close the cursor
    CLOSE staff_cursor;
END;
/
