-- Insurance Table
INSERT INTO insurance(insurance_id, provider, policy_no, valid_until, created_at)
VALUES
(1, 'Star Health', 'SH123456789', '2037-12-31', CURRENT_TIMESTAMP),
(2, 'ICICI Lombard', 'ICL987654321', '2036-10-25', CURRENT_TIMESTAMP),
(3, 'HDFC ERGO', 'HE456789123', '2038-05-30', CURRENT_TIMESTAMP),
(4, 'New India Assurance', 'NIA741852963', '2039-03-20', CURRENT_TIMESTAMP),
(5, 'Care Health', 'CH369258147', '2037-08-31', CURRENT_TIMESTAMP);

-- Patient Table
INSERT INTO patient(patient_id,patient_name,gender,dob,email,createdtime,blood_group,insurance_id)
VALUES(101, 'Rahul Sharma', 'Male', '1995-04-12', 'rahul.sharma@email.com',CURRENT_TIMESTAMP , 'B_POSITIVE', 1),
(102, 'Priya Patel', 'Female', '1998-08-23',  'priya.patel@email.com',CURRENT_TIMESTAMP , 'A_POSITIVE', 2),
(103, 'Amit Verma', 'Male', '1987-11-15', 'amit.verma@email.com', CURRENT_TIMESTAMP, 'O_POSITIVE', 3),
(104, 'Sneha Joshi', 'Female', '1992-06-30',  'sneha.joshi@email.com',CURRENT_TIMESTAMP , 'AB_POSITIVE', 4),
(105, 'Karan Mehta', 'Male', '2000-02-18', 'karan.mehta@email.com', CURRENT_TIMESTAMP, 'A_NEGATIVE', 5);