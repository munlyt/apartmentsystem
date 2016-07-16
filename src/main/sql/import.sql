INSERT INTO `apartmentsystem`.`role` (`roleType`) VALUES ('ROLE_ADMIN');
INSERT INTO `apartmentsystem`.`role` (`roleType`) VALUES ('ROLE_MANAGER');
INSERT INTO `apartmentsystem`.`role` (`roleType`) VALUES ('ROLE_EMPLOYEE');

INSERT INTO `apartmentsystem`.`user` (`id`, `email`, `name`, `password`, `status`, `username`) VALUES ('1', 'chandra.panday.5@gmail.com', 'Chandra Prakash Panday', '$2a$10$vY6mMqlADKz3JLabjoqXJeArU2S4YtF96sTIgzR9HimE/2Nx3uGE6', 'ACTIVE', 'chandra');

INSERT INTO `apartmentsystem`.`user_role` (`id`, `role_id`, `user_id`) VALUES ('1', '1', '1');

