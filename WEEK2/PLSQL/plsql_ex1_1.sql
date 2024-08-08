DECLARE
  v_calculated_age NUMBER;
  v_new_interest_rate Loans.InterestRate%TYPE;
BEGIN
  FOR loan_record IN (
    SELECT c.CustomerID AS customer_id, c.DOB AS date_of_birth, l.LoanID AS loan_id, l.InterestRate AS current_interest_rate
    FROM Customers c
    JOIN Loans l ON c.CustomerID = l.CustomerID
  ) LOOP
    v_calculated_age := FLOOR(MONTHS_BETWEEN(SYSDATE, loan_record.date_of_birth) / 12);
    IF v_calculated_age > 60 THEN
      UPDATE Loans
      SET InterestRate = InterestRate - 1,
          LastModified = SYSDATE
      WHERE LoanID = loan_record.loan_id;

      SELECT InterestRate 
      INTO v_new_interest_rate 
      FROM Loans l 
      WHERE l.LoanID = loan_record.loan_id;

      DBMS_OUTPUT.PUT_LINE('Applied 1% discount to Customer ID: ' || loan_record.customer_id || ', New Interest Rate: ' || v_new_interest_rate);
    END IF;
  END LOOP;
END;
/
