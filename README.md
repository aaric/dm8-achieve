# dm8-achieve

[![License](https://img.shields.io/badge/License-GPL--3.0-green.svg?style=flat&logo=unlicense)](https://www.gnu.org/licenses/gpl-3.0.html)
[![OracleJDK](https://img.shields.io/badge/OracleJDK-1.8.0__202-success.svg?style=flat&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI2NCIgaGVpZ2h0PSI2NCIgdmlld0JveD0iMCAwIDMyIDMyIj48cGF0aCBkPSJNMTEuNjIyIDI0Ljc0cy0xLjIzLjc0OC44NTUuOTYyYzIuNTEuMzIgMy44NDcuMjY3IDYuNjI1LS4yNjdhMTAuMDIgMTAuMDIgMCAwIDAgMS43NjMuODU1Yy02LjI1IDIuNjcyLTE0LjE2LS4xNi05LjI0NC0xLjU1em0tLjgtMy40NzNzLTEuMzM2IDEuMDE1Ljc0OCAxLjIzYzIuNzI1LjI2NyA0Ljg2Mi4zMiA4LjU1LS40MjdhMy4yNiAzLjI2IDAgMCAwIDEuMjgyLjgwMWMtNy41MzQgMi4yNDQtMTUuOTc2LjIxNC0xMC41OC0xLjYwM3ptMTQuNzQ3IDYuMDlzLjkwOC43NDgtMS4wMTUgMS4zMzZjLTMuNTggMS4wNy0xNS4wMTQgMS4zOS0xOC4yMiAwLTEuMTIyLS40OCAxLjAxNS0xLjE3NSAxLjctMS4yODIuNjk1LS4xNiAxLjA3LS4xNiAxLjA3LS4xNi0xLjIzLS44NTUtOC4xNzUgMS43NjMtMy41MjYgMi41MSAxMi43NyAyLjA4NCAyMy4yOTYtLjkwOCAxOS45ODMtMi40MDR6TTEyLjIgMTcuNjMzcy01LjgyNCAxLjM5LTIuMDg0IDEuODdjMS42MDMuMjE0IDQuNzU1LjE2IDcuNjk0LS4wNTMgMi40MDQtLjIxNCA0LjgxLS42NCA0LjgxLS42NHMtLjg1NS4zNzQtMS40NDMuNzQ4Yy01LjkzIDEuNTUtMTcuMzEyLjg1NS0xNC4wNTItLjc0OCAyLjc3OC0xLjMzNiA1LjA3Ni0xLjE3NSA1LjA3Ni0xLjE3NXptMTAuNDIgNS44MjRjNS45ODQtMy4xIDMuMjA2LTYuMDkgMS4yODItNS43MTctLjQ4LjEwNy0uNjk1LjIxNC0uNjk1LjIxNHMuMTYtLjMyLjUzNC0uNDI3YzMuNzk0LTEuMzM2IDYuNzg2IDQuMDA3LTEuMjMgNi4wOSAwIDAgLjA1My0uMDUzLjEwNy0uMTZ6bS05LjgzIDguNDQyYzUuNzcuMzc0IDE0LjU4Ny0uMjE0IDE0LjgtMi45NCAwIDAtLjQyNyAxLjA3LTQuNzU1IDEuODctNC45MTYuOTA4LTExLjAwNy44LTE0LjU4Ny4yMTQgMCAwIC43NDguNjQgNC41NDIuODU1eiIgZmlsbD0iIzRlNzg5NiIvPjxwYXRoIGQ9Ik0xOC45OTYuMDAxczMuMzEzIDMuMzY2LTMuMTUyIDguNDQyYy01LjE4MyA0LjExNC0xLjE3NSA2LjQ2NSAwIDkuMTM3LTMuMDQ2LTIuNzI1LTUuMjM2LTUuMTMtMy43NC03LjM3M0MxNC4yOTQgNi44OTMgMjAuMzMyIDUuMyAxOC45OTYuMDAxem0tMS43IDE1LjMzNWMxLjU1IDEuNzYzLS40MjcgMy4zNjYtLjQyNyAzLjM2NnMzLjk1NC0yLjAzIDIuMTM3LTQuNTQyYy0xLjY1Ni0yLjQwNC0yLjk0LTMuNTggNC4wMDctNy41ODcgMCAwLTEwLjk1MyAyLjcyNS01LjcxNyA4Ljc2M3oiIGZpbGw9IiNmNTgyMTkiLz48L3N2Zz4=)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![JUnit](https://img.shields.io/badge/JUnit-5.6.2-success.svg?style=flat&logo=junit5)](https://junit.org/junit5/docs/current/user-guide)
[![Gradle](https://img.shields.io/badge/Gradle-7.4.2-success.svg?style=flat&logo=gradle)](https://docs.gradle.org/7.4/userguide/installation.html)
[![Release](https://img.shields.io/badge/Release-0.2.0-informational.svg)](https://github.com/aaric/dm8-achieve/releases)

> *[达梦线上实验室。](https://eco.dameng.com/tour/)*  
> *[Java应用开发指南。](https://eco.dameng.com/document/dm/zh-cn/app-dev/java-jdbc.html)*

## 1 基本特性

### 1.1 数据库

```sql
-- 查看数据库运行状态
SELECT status$ FROM v$instance;

-- 查看版本信息
SELECT banner FROM V$version;
```

### 1.2 用户权限

```sql
-- 创建用户
CREATE USER test IDENTIFIED BY "test123abc";

-- 授予用户基本权限
GRANT RESOURCE TO test;
GRANT SELECT ON dmhr.employee TO test;
GRANT SELECT ON dmhr.department TO test;

-- 查看用户信息
SELECT
  username, account_status, created FROM dba_users
WHERE username = upper('test');

-- 切换用户
CONN test/test123abc;

-- 查看当前登录用户
SELECT user FROM dual;
```

### 1.3 操作数据表

```sql
-- 创建 department 表
CREATE TABLE department
(
  department_id INTEGER PRIMARY KEY,
  department_name VARCHAR(30) NOT NULL
);

-- 创建 employee 表
CREATE TABLE employee
(
  employee_id INTEGER,
  employee_name VARCHAR2(20) NOT NULL,
  hire_date DATE,
  salary INTEGER,
  department_id INTEGER NOT NULL
);

-- 添加表约束
ALTER TABLE employee MODIFY (hire_date NOT NULL);
ALTER TABLE employee ADD CONSTRAINT pk_empid PRIMARY KEY (employee_id);
ALTER TABLE employee ADD CONSTRAINT fk_dept FOREIGN KEY (department_id) 
  REFERENCES department (department_id);

-- 查看表结构
DESC employee;

-- 查看表主键外键
SELECT
  table_name, constraint_name, constraint_type
FROM all_constraints
WHERE owner = upper('test') AND table_name = upper('employee');
```

### 1.4 检索数据

```sql
-- 插入数据
INSERT INTO department VALUES (1, '数据库产品中心');
INSERT INTO employee VALUES (1, '达梦V8','2008-05-30 00:00:00', 30000, 1);
COMMIT;

-- 修改数据
UPDATE employee SET salary = '35000' WHERE employee_id = 1;
COMMIT;

-- 查询数据
SELECT employee_id, salary FROM employee;

-- 删除数据
DELETE FROM employee;
DELETE FROM department WHERE department_id = 1;

-- 批量插入数据
CREATE TABLE t1 AS
  SELECT rownum AS id,
    trunc(dbms_random.value(0, 100)) AS random_id,
    dbms_random.string('x', 20) AS random_string
  FROM dual
CONNECT BY level <= 100000;

-- 查询数据条数
SELECT COUNT(*) FROM t1;

-- 排序数据
SELECT * FROM t1 where rownum < 10 ORDER BY random_id DESC;

-- 分组查询
INSERT INTO department (department_id, department_name)
  SELECT department_id, department_name FROM dmhr.department;
INSERT INTO employee (employee_id, employee_name, hire_date, salary, department_id)
  SELECT employee_id, employee_name, hire_date, salary, department_id FROM dmhr.employee;
COMMIT;

SELECT
  dept.department_name as Department, count(*) as Total
FROM employee emp, department dept
WHERE emp.department_id=dept.department_id
GROUP BY dept.department_name
HAVING count(*) > 20;

-- 定义视图
CREATE OR REPLACE VIEW v1 AS
  SELECT
    dept.department_name, emp.employee_name, emp.salary, emp.hire_date
  FROM employee emp, department dept
  WHERE salary > 10000
  AND hire_date >= '2013-08-01'
  AND emp.department_id = dept.department_id;

-- 查询视图
SELECT * FROM v1 WHERE salary < 12000;
```

### 1.5 创建索引

```sql
-- 创建普通索引
CREATE INDEX idx_emp_salary ON employee(salary);

-- 查看索引结构
SELECT
  table_name, index_name, index_type
FROM user_indexes WHERE index_name = upper('idx_emp_salary');

-- 删除索引
DROP INDEX idx_emp_salary;
```

### 1.6 事务特性

```sql
-- 插入数据
INSERT INTO employee VALUES (1, '达梦V8', '2020-05-30 00:00:00', 50000, 1);

-- 创建保存点
SAVEPOINT my_insert;

-- 更新数据记录
UPDATE employee SET department_id = 2 WHERE employee_id = 1;

-- 不提交（回滚）查看数据记录
SELECT department_id, employee_id FROM employee WHERE employee_id = 1;

-- 回滚到保存点
ROLLBACK TO my_insert;
```

### 1.7 其他

1. 序列

```sql
-- 创建序列
CREATE SEQUENCE seq1
  START WITH 1 INCREMENT BY 1 MAXVALUE 10000
  CACHE 5 NOCYCLE;

-- 查询下一个序列号
SELECT seq1.nextval() FROM dual;

-- 查询当前序列号
SELECT seq1.currval() FROM dual;
```

2. [物化视图](https://eco.dameng.com/document/dm/zh-cn/sql-dev/practice-mv.html)

```sql
-- 定义物化视图
CREATE MATERIALIZED VIEW mv1 
  BUILD IMMEDIATE REFRESH COMPLETE ON COMMIT AS
  SELECT department_id as depo_no, count(*) as total FROM employee GROUP BY department_id;

-- 查看（验证）物化视图
SELECT * FROM mv1 WHERE depo_no = 1;

-- 插入测试数据
INSERT INTO employee VALUES (2, '达梦V10', '2020-05-31 00:00:00', 60000, 1);
```

3. [函数](https://eco.dameng.com/document/dm/zh-cn/sql-dev/practice-func.html)

```sql
-- 创建函数
CREATE OR REPLACE FUNCTION random_password (len IN NUMBER)
RETURN VARCHAR2
AS
  pwdstr VARCHAR2(128);
BEGIN
  pwdstr = dbms_random.string('x', len);
  RETURN pwdstr;
END;

-- 调用函数
SELECT random_password(12) FROM dual;

-- 删除函数
DROP FUNCTION random_password;
```

4. [存储过程](https://eco.dameng.com/document/dm/zh-cn/sql-dev/practice-pro.html)

```sql
-- 准备数据表
CREATE TABLE pro_test
(
  id INTEGER PRIMARY KEY,
  name VARCHAR(30)
);

-- 创建存储过程
CREATE OR REPLACE PROCEDURE pro1 (n IN INTEGER)
AS
  j INTEGER;
BEGIN
  FOR j IN 1..n LOOP
    INSERT INTO pro_test VALUES (j, 'test'|| j);
  END LOOP;
END;

-- 调用存储过程
BEGIN
  pro1(5);
END;

-- 查询数据
SELECT * FROM pro_test;

-- 删除存储过程
DROP PROCEDURE pro1;
```

5. [触发器](https://eco.dameng.com/document/dm/zh-cn/sql-dev/practice-trg.html)

```sql
-- 准备数据表+数据
CREATE TABLE trg_test
(
  id INTEGER PRIMARY KEY,
  name VARCHAR(30)
);
CREATE TABLE trg_test_log
(
  name_old VARCHAR(100),
  name_new VARCHAR(100)
);
INSERT INTO trg_test VALUES (1, 'zhangsan');

-- 创建触发器
CREATE OR REPLACE TRIGGER trg1
BEFORE UPDATE OF name ON trg_test
FOR EACH ROW
DECLARE
BEGIN
  INSERT INTO trg_test_log VALUES (:old.name, :new.name);
END;

-- 更新数据
UPDATE trg_test SET name = 'lisi' WHERE id = 1;

-- 查询数据
SELECT * FROM trg_test_log;
```

6. [分区表](https://eco.dameng.com/document/dm/zh-cn/sql-dev/advanced-partitoning.html)

```sql
-- NA
```

## 2 Java应用开发指南

### 2.1 测试表

```sql
-- 创建序列
CREATE SEQUENCE test_seq
  START WITH 1 INCREMENT BY 1 NOMAXVALUE
  CACHE 5 NOCYCLE;

-- 创建测试表
CREATE TABLE test
(
  id INTEGER PRIMARY KEY DEFAULT test_seq.nextval,
  name VARCHAR(30)
);

COMMENT ON TABLE test IS '测试表';
COMMENT ON COLUMN test.id IS '主键ID';
COMMENT ON COLUMN test.name IS '名称';
```

### 2.2 Gradle 依赖

```groovy
implementation "com.dameng:Dm8JdbcDriver18"
```
