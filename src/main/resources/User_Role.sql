DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login`  (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
   `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
   `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES (11, 'root', '$2a$10$5MHtE2x/zgcaX1LDlhSol.cNplGoxNkpzKRfMMs1TJq0SnoUEeHBO', 'root');
INSERT INTO `user_login` VALUES (12, 'admin', '$2a$10$cINjnNUDzDRWC08IYZPBhejBBj0wMKR9vOjIs0aDa0lu6Z9VOYG3y', 'admin');
INSERT INTO `user_login` VALUES (13, 'wangjia', '$2a$10$VP/x/e941LLmCo5jO7SBuugEWLffY/F.p63AzlcS3LN47ETm4.g4i', 'wangjia');




-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
 `nameZh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_dba', '数据库管理员');
INSERT INTO `role` VALUES (2, 'ROLE_admin', '系统管理员');
INSERT INTO `role` VALUES (3, 'ROLE_userpw', '用户');




-- ----------------------------
-- Table structure for user_login_role
-- ----------------------------
DROP TABLE IF EXISTS `user_login_role`;
CREATE TABLE `user_login_role`  (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `uid` int(11) NULL DEFAULT NULL,
                                    `rid` int(11) NULL DEFAULT NULL,
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_login_role
-- ----------------------------
INSERT INTO `user_login_role` VALUES (1, 11, 1);
INSERT INTO `user_login_role` VALUES (2, 11, 2);
INSERT INTO `user_login_role` VALUES (3, 12, 2);
INSERT INTO `user_login_role` VALUES (4, 13, 3);