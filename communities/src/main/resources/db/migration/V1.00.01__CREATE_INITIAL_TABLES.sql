CREATE TABLE IF NOT EXISTS TB_USER(
    id BIGSERIAL primary key,
    uuid varchar(150) unique not null,
    userName varchar(250) not null
);

CREATE TABLE IF NOT EXISTS TB_COMMUNITY(
    id BIGSERIAL primary key,
    uuid varchar(150) unique not null,
    community_Name varchar(150) not null,
    community_leader_id integer not null,
    member_quantity integer default null,
    description varchar(250) default null,

    FOREIGN KEY (community_leader_id) REFERENCES TB_USER(id)
);

CREATE TABLE IF NOT EXISTS TB_ROOM(
    id BIGSERIAL primary key,
    uuid varchar(150) unique not null,
    roomName varchar(150) not null,
    is_voice boolean,
    community_id integer,
    FOREIGN KEY(community_id) REFERENCES TB_COMMUNITY(id)
);

CREATE TABLE IF NOT EXISTS TB_SERVER_MEMBER(
    id BIGSERIAL primary key,
    community_id integer not null,
    member_id integer not null,
    FOREIGN KEY(community_id) REFERENCES TB_COMMUNITY(id),
    FOREIGN KEY(member_id) REFERENCES TB_USER(id)
);

CREATE TABLE IF NOT EXISTS TB_SERVER_LEADER(
    id BIGSERIAL primary key,
    community_id integer not null,
    leader_id integer not null,
    FOREIGN KEY(community_id) REFERENCES TB_COMMUNITY(id),
    FOREIGN KEY(leader_id) REFERENCES TB_USER(id)
);

CREATE TABLE IF NOT EXISTS TB_TAG(
  id BIGSERIAL primary key,
  tag_name varchar(150)
);

CREATE TABLE IF NOT EXISTS TB_TAG_SERVER(
    id BIGSERIAL primary key,
    community_id integer not null,
    tag_id integer not null,

    FOREIGN KEY(community_id) REFERENCES TB_COMMUNITY(id),
    FOREIGN KEY(tag_id) REFERENCES TB_TAG(id)
);