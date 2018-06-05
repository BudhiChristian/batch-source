CREATE TABLE ACCOUNT (
    ACC_ID INT,
    ACC_USERNAME VARCHAR(50),
    ACC_PASSWORD VARCHAR(50),
    ACC_BALANCE NUMBER(12,2)
);

-- GENERATE PRIMARY KEYS FOR ACCOUNT
CREATE SEQUENCE SQ_ACCOUNTS_PK
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TR_INSERT_ACCOUNT
BEFORE INSERT ON ACCOUNT
FOR EACH ROW
BEGIN
    SELECT SQ_ACCOUNTS_PK.NEXTVAL INTO :NEW.ACC_ID FROM DUAL;
END;
/

CREATE OR REPLACE PROCEDURE DELETE_ACCOUNT (ACC_ID IN NUMBER)
AS
BEGIN
    DELETE FROM ACCOUNT
    WHERE ACC_ID = ACC_ID;
END;
/