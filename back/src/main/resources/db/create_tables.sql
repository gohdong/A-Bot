-- Drop table user_directory;
CREATE TABLE IF NOT EXISTS user_directory(
    `id` BIGINT NOT NULL,
    `parent_id` BIGINT NOT NULL,
    `name` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `is_file` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`id`)
);