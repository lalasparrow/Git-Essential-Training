Cloud9

	mysql-clt start
	mysql-clt cli
	mysql-clt end


mySQL
	exit;
	help;
	show databases;
	select @@hostname;

Start mySQL:
	mysql -u root -p

mySQL:
	CREATE DATABASE <name>;
	DROP DATABASE <name>;
	USE <database name>; // switch between databases
	SELECT database(); // 查看当前的database是哪个


	如果要是drop了当前使用的database，用"select database();"，就会出现NULL：
		+------------+
		| database() |
		+------------+
		| NULL       |
		+------------+
		1 row in set (0.00 sec)

Lecture 30
	Numberic Types: INT(*, default: [-2147483648,2147483647]), SMALLINT, TINYINT, MEDIUMINT, BIGINT, DECIMAL, NUMERIC, FLOAT, DOUBLE, BIT
	
	String Types: CHAR(*, fixed length), VERCHAR(*, =text/string, 长度可变，[1,255] characters), BINARY, VARBINARY, BLOB, TINYBLOB, MEDIUMBLOB, LONGBLOB, TEXT, TINYTEXT, MEDIUMTEXT, LONGTEXT, ENUM
	
	Date Types: DATE, DATETIME, TIMESTAMP, TIME, YEAR


Lecture 34
	CREATE TABLE tablename
	  (
	    column_name data_type,
	    column_name data_type
	  );

	example:
	CREATE TABLE cats
	  (
	    name VARCHAR(100),
	    age INT
	  );


Lecture 36
	SHOW TABLES;

		+-------------------+
		| Tables_in_cat_app |
		+-------------------+
		| cats              |
		+-------------------+


	SHOW COLUMNS FROM tablename;
 
	// SHOW COLUMNS FROM cats;
		+-------+--------------+------+-----+---------+-------+
		| Field | Type         | Null | Key | Default | Extra |
		+-------+--------------+------+-----+---------+-------+
		| name  | varchar(100) | YES  |     | NULL    |       |
		| age   | int(11)      | YES  |     | NULL    |       |
		+-------+--------------+------+-----+---------+-------+
	

	DESC tablename;

	// DESC cats;
		+-------+--------------+------+-----+---------+-------+
		| Field | Type         | Null | Key | Default | Extra |
		+-------+--------------+------+-----+---------+-------+
		| name  | varchar(100) | YES  |     | NULL    |       |
		| age   | int(11)      | YES  |     | NULL    |       |
		+-------+--------------+------+-----+---------+-------+

Lecture 38
	DROP TABLE <tablename>;



Lecture 43
	Inserting Data

	The "formula":

	INSERT INTO table_name(column_name) VALUES (data);
	For example:

	INSERT INTO cats(name, age) VALUES ('Jetson', 7);

Lecture 45
	view 所有的data
	View all of our data in any given table select star from the table names or select start from

	SELECT * FROM cats;
		+----------+------+
		| name     | age  |
		+----------+------+
		| Jetson   |    7 |
		| Victoria |    9 |
		+----------+------+

Lecture 47
	INSERT INTO table_name 
	            (column_name, column_name) 
	VALUES      (value, value), 
	            (value, value), 
	            (value, value);

 
Lecture 52 Warnings
	SHOW WARNINGS;


Lecture 54 NULL and NOT_NULL
	在表中有一列是"NULL"，如果是YES的话，说明该field可以是NULL
	在create的时候，标注NOT_NULL就可以不允许这个值为空
	CREATE TABLE cat2
		(
			name VARCHAR(100) NOT NULL,
			age INT NOT NULL
		);

	如果此时再插入一个nameless cat，就会有warning

Lecture 56 Setting Default Values
	设置default value:

	CREATE TABLE cat3 
		(
			name VARCHAR(100) DEFAULT 'unnamed',
			age INT DEFAULT 99
		);



	同时设置default value和not_null:
	CREATE TABLE cat4
		(
			name VARCHAR(100) NOT NULL DEFAULT 'unnamed',
			age INT NOT NULL DEFAULT 99

		);


	如果设置的null，还可以insert一个null value；但是，如果设置一个not_null的话，就不能insert一个null value了

Lecture 58
	Primary Key: a unique identifier
	Assign a primary key:
		CREATE TABLE unique_cats (
		    cat_id INT NOT NULL,
		    name VARCHAR(100),
		    age INT,
		    PRIMARY KEY (cat_id)
		  );
	自动生成一个primary key:
		CREATE TABLE unique_cats2 (
		    cat_id INT NOT NULL AUTO_INCREMENT,
		    name VARCHAR(100),
		    age INT,
		    PRIMARY KEY (cat_id)
		);
 


Section 5
Lecture 63
	CRUD = create + read + update + delete


	Lecture 68
	SELECT * FROM cats;
		   |
		"Give me all columns"


	SELECT name FROM cats;
	SELECT age, breed, name, cat_id FROM cats; // 显示的顺序会变


Lecture 70 WHERE
	SELECT * FROM cats WHERE age=4; // * 表示显示的columns
	SELECT * FROM cats WHERE name='Egg'; // case-insensitive, 'EGG'也可以返回结果

Lecture 75 Aliases
	可以显示column name的别称，比如有两个table，一个cats，一个dogs，都有name，这时候就可以用aliase

	SELECT cat_id AS id, name FROM cats;
 
	SELECT name AS 'cat name', breed AS 'kitty breed' FROM cats;
	 
	DESC cats;

Lecture 77 Update(CRUD)
	UPDATE cats SET breed='Shorthair'
	WHERE breed='Tabby';

Lecture 82 Delect
	DELETE FROM cats WHERE name='Egg';	



Lecture 99 Running MySQL files
	source filename.sql       // 在mysql运行的时候
	source testing/insert.sql


Lecture 103 CONCAT
想要把几个column连起来的时候
	CONCAT(column, anotherColumn)
	CONCAT(column, 'text', anotherColumn, 'more text')

		SELECT
		  CONCAT(author_fname, ' ', author_lname)
		FROM books;
		 
		SELECT
		  CONCAT(author_fname, ' ', author_lname)
		  AS 'full name'
		FROM books;


		SELECT author_fname AS first, author_lname AS last, 
		  CONCAT(author_fname, ' ', author_lname) AS full
		FROM books;
		 
		SELECT author_fname AS first, author_lname AS last, 
		  CONCAT(author_fname, ', ', author_lname) AS full
		FROM books;


	CONCAT_WS //concat with seperater
	CONCAT(title, '-', author_fname, '-', author_lname)
<=> CONCAT_WS(' - ', title, author_fname, author_lname)

	SELECT CONCAT(title, '-', author_fname, '-', author_lname) FROM books;
	 
	SELECT 
	    CONCAT_WS(' - ', title, author_fname, author_lname) 
	FROM books;


Lecture 105 SUBSTRING//SUBSTR
	SELECT SUBSTRING('Hello World', 1, 4); //这里H的index是1，不是0， 'Hello'
	SELECT SUBSTRING('Hello World', 7); //World
	SELECT SUBSTRING('Hello World', -3); //rld

	


