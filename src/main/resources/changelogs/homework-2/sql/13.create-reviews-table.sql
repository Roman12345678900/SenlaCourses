create table reviews (
    id bigserial primary key not null,
    rating int not null,
    review_text varchar(500) not null,
    review_date date not null,
    class_id bigint not null,
    user_id bigint not null,
    foreign key (class_id) references classes(id),
    foreign key (user_id) references users(id)
);


--rollback drop table reviews;