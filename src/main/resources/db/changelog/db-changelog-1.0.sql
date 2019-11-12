create sequence poll_question_seq;
create sequence poll_seq;
create table poll
(
    poll_id    bigint not null
        constraint poll_pkey
            primary key,
    active     boolean,
    end_date   date,
    poll_name  varchar(255),
    start_date date
);

create table poll_question
(
    poll_question_id bigint not null
        constraint poll_question_pkey
            primary key,
    number           integer,
    question         varchar(255),
    poll_url     varchar(255),
    poll_poll_id     bigint
        constraint poll_question_fkey
            references poll on delete cascade
);
