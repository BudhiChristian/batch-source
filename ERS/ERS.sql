CREATE TABLE EMPLOYEE(
USERID NUMBER CONSTRAINT PK_EMP PRIMARY KEY,
USERNAME VARCHAR2(50),
EMP_PASS VARCHAR2(50),
FNAME VARCHAR2(50),
LNAME VARCHAR2(50),
BIRTHDAY DATE,
EMAIL VARCHAR2(50),
PHONE NUMBER,
STREET VARCHAR2(50),
CITY VARCHAR2(50),
STATES VARCHAR2(50),
POSTCODE NUMBER
)

INSERT INTO EMPLOYEE VALUES (2, 'JOHN123', '1234', 'JOHN', 'HOG', TO_DATE('20020315', 'yyyymmdd'), 'JOHNHOG@GMAIL.COM', 8564568666, 
'1677 HELLO STREET', 'COLIN', 'VA', 08049);
INSERT INTO MANAGER VALUES (1, 'MAN123', '1234', 'FLOE', 'ZOE', 'FZ@GMAIL.COM', 4986358631, '1 A STREET', 'AS', 'AZ', 09011);


SELECT * FROM EMPLOYEE WHERE USERNAME='JOHN123' AND EMP_PASS='1234'

CREATE TABLE MANAGER(
MANAGER_ID NUMBER CONSTRAINT PK_MAN PRIMARY KEY,
USERNAME VARCHAR2(50),
MAN_PASS VARCHAR2(50),
FNAME VARCHAR2(50),
LNAME VARCHAR2(50),
EMAIL VARCHAR2(50),
PHONE NUMBER,
STREET VARCHAR2(50),
CITY VARCHAR2(50),
STATES VARCHAR2(50),
POSTCODE NUMBER
)

CREATE TABLE RR(
REIMBURSEMENT_ID NUMBER CONSTRAINT PK_RR PRIMARY KEY,
REQUESTER VARCHAR2(50),
REQUESTER_ID NUMBER CONSTRAINT FK_RR REFERENCES EMPLOYEE,
TOTAL_AMOUNT NUMBER,
REASON VARCHAR2(300),
BILL_DATE DATE,
APPROVE_DATE DATE,
MANAGERAPPROVED VARCHAR2(50),
STATUS VARCHAR2(50)
)

CREATE SEQUENCE SQ_EMP_ID 
START WITH 5
INCREMENT BY 1;
CREATE SEQUENCE SQ_MAN_ID 
START WITH 2
INCREMENT BY 1;
CREATE SEQUENCE SQ_REI_ID 
START WITH 5
INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TR_INSERT_REI
BEFORE INSERT ON RR
FOR EACH ROW
BEGIN
    SELECT SQ_REI_ID.NEXTVAL INTO :NEW.REIMBURSEMENT_ID FROM DUAL;
END;
CREATE OR REPLACE TRIGGER TR_INSERT_EMP
BEFORE INSERT ON EMPLOYEE
FOR EACH ROW
BEGIN
    SELECT SQ_EMP_ID.NEXTVAL INTO :NEW.USERID FROM DUAL;
END;