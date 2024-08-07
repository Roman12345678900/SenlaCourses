create table reviews (
    id bigserial primary key,
    rating int not null,
    review_text text,
    review_date date not null,
    class_id bigint not null,
    user_id bigint not null,
    foreign key (class_id) references classes(id),
    foreign key (user_id) references users(id)
);

--rollback drop table reviews;