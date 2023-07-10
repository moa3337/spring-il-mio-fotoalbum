INSERT INTO `photos`(`id`, `created_at`, `description`, `img`, `title`, `visible`) VALUES (1,'2023-07-06 09:00:00','Foto mare estate 2023, senza filtri','https://www.campania.info/wp-content/uploads/sites/111/paestum-mare-hd.jpg','Mare',1)

INSERT INTO `categories`(`id`, `category_name`, `created_at`) VALUES (1,'Natura','2023-07-06 09:00:00')
INSERT INTO `categories`(`id`, `category_name`, `created_at`) VALUES (2,'Città','2023-07-06 09:00:00')
INSERT INTO `categories`(`id`, `category_name`, `created_at`) VALUES (3,'Animali','2023-07-06 09:00:00')
INSERT INTO `categories`(`id`, `category_name`, `created_at`) VALUES (4,'Veicoli','2023-07-06 09:00:00')
INSERT INTO `categories`(`id`, `category_name`, `created_at`) VALUES (5,'Viaggi','2023-07-06 09:00:00')

INSERT INTO `roles`(`id`, `name`) VALUES (1,'ADMIN')
INSERT INTO `roles`(`id`, `name`) VALUES (2,'USER')
INSERT INTO `users`(`id`, `email`, `first_name`, `last_name`, `password`) VALUES (1,'john@email.com','John','Doe','{noop}john')
INSERT INTO `users`(`id`, `email`, `first_name`, `last_name`, `password`) VALUES (2,'jane@email.com','Jane','Doe','{noop}jane')
INSERT INTO `users_roles`(`roles_id`, `user_id`) VALUES (1, 1);
INSERT INTO `users_roles`(`roles_id`, `user_id`) VALUES (2, 2);