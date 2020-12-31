
create table d_member(

	m_id		varchar2(20),	
	m_pw		varchar2(20),
	m_name		varchar2(20),
	m_age		number,
	m_gender	varchar2(10),
	m_email		varchar2(50),
	PRIMARY KEY (m_id)
);


select * from d_member;


create table d_board (
	b_num		number,
	b_id		varchar2(20),
	b_title		varchar2(50),
	b_content	varchar2(3000),
	b_file		varchar2(50),
	b_readcount	number,
	b_good		number,
	b_date		date,
	PRIMARY KEY(b_num)
);

select * from d_board;
delete from d_board where B_ID = 'null';

update d_board set b_good=0;
alter table d_board
modify(b_title varchar2(200));

alter table 
