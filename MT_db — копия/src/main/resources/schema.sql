create table auth
(
	id serial not null
		constraint auth_pkey
			primary key,
	user_id bigint,
	cookie_value varchar(100)
)
;

create table users
(
	id serial not null
		constraint users_pkey
			primary key,
	name varchar(25),
	email varchar(25),
	passwordhash varchar(1000)
)
;

alter table auth
	add constraint auth_user_id_fkey
		foreign key (user_id) references users
;

create table cart
(
	id serial not null
		constraint cart_pkey
			primary key,
	owner_id bigint
		constraint cart_owner_id_fkey
			references users
)
;

create table cart_product
(
	product_id bigint,
	cart_id bigint
		constraint cart_product_cart_id_fkey
			references cart,
	count integer default 1
)
;

create table product
(
	id serial not null
		constraint product_pkey
			primary key,
	name varchar(25),
	cost bigint
)
;

alter table cart_product
	add constraint cart_product_product_id_fkey
		foreign key (product_id) references product
;


