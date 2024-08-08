CREATE OR REPLACE PROCEDURE TransferFunds(
    p_source_account_id IN Accounts.AccountID%TYPE,  -- Source account ID
    p_target_account_id IN Accounts.AccountID%TYPE,  -- Target account ID
    p_amount IN NUMBER                               -- Amount to transfer
) IS
    v_source_balance Accounts.Balance%TYPE;        -- Variable to store the source account balance
BEGIN
    -- Check if the source account has sufficient balance
    SELECT Balance
    INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_source_account_id
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient balance in source account.');
    END IF;

    -- Update the source account
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_source_account_id;

    -- Update the target account
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_target_account_id;

    -- Commit the transaction
    COMMIT;

    -- Output success message
    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully: ' ||
                         'From AccountID ' || p_source_account_id || 
                         ' To AccountID ' || p_target_account_id ||
                         ' Amount ' || p_amount);
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('One or both account IDs do not exist.');
    WHEN OTHERS THEN
        -- Rollback in case of an error
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
BEGIN
  TransferFunds(p_source_account_id => 1, 
                p_target_account_id => 2, 
                p_amount => 500);
END;
/
