-- DROP TABLE IF EXISTS t_user;
CREATE TABLE if not exists `t_user` (
                          `id` int NOT NULL,
                          `user_name` varchar(45) DEFAULT NULL,
                          `order_no` varchar(45) DEFAULT NULL,
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
