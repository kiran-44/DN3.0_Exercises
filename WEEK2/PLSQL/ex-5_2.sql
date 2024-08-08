-- Create ActivityLog Table
BEGIN
  EXECUTE IMMEDIATE 'CREATE TABLE ActivityLog (
    LogID NUMBER PRIMARY KEY,
    TransID NUMBER,
    TransDate DATE,
    TransAmount NUMBER,
    TransType VARCHAR2(10),
    LogDate DATE
  )';
END;
/

-- Create Sequence for ActivityLog
BEGIN
  EXECUTE IMMEDIATE 'CREATE SEQUENCE ActivityLog_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE';
END;
/

-- Create Trigger for Logging Transactions
CREATE OR REPLACE TRIGGER RecordTransaction
AFTER INSERT ON Ledger
FOR EACH ROW
BEGIN
  INSERT INTO ActivityLog (LogID, TransID, TransDate, TransAmount, TransType, LogDate)
  VALUES (ActivityLog_SEQ.NEXTVAL, :NEW.LedgerID, :NEW.DateOfTransaction, :NEW.TransactionAmount, :NEW.TypeOfTransaction, SYSDATE);
END;
/
