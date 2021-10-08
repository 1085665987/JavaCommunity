/*
 Navicat Premium Data Transfer

 Source Server         : 172.20.1.62(zhaow)
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 172.20.1.62:36306
 Source Schema         : zhaow

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 24/09/2021 08:41:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device_log
-- ----------------------------
DROP TABLE IF EXISTS `device_log`;
CREATE TABLE `device_log`  (
  `log_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deviceID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deviceTypeDef` int(11) NULL DEFAULT NULL,
  `deviceName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deviceManufactorName` int(11) NULL DEFAULT NULL,
  `deviceModel` int(11) NULL DEFAULT NULL,
  `deviceProductionDate` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `deviceInstallDate` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `deviceOrientation` int(11) NOT NULL,
  `deviceTele` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deviceMeid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deviceNetTypeDef` int(11) NULL DEFAULT NULL,
  `deviceRunStateDef` int(11) NULL DEFAULT NULL,
  `deviceShieldStateDef` int(11) NULL DEFAULT NULL,
  `mainFunction` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `installPerson` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guarder` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `collectServerId` int(11) NULL DEFAULT NULL,
  `deviceGuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detailMainFunction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `primaryMainFunction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channelCount` int(11) NULL DEFAULT NULL,
  `tempCount` int(11) NULL DEFAULT NULL,
  `partsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deviceSTAId` int(11) NULL DEFAULT NULL,
  `deviceSTAFlag` int(11) NULL DEFAULT NULL,
  `deviceParentId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父设备编号',
  `deputyDeviceOrientation` int(11) NULL DEFAULT NULL,
  `isAllowRepeatAlarm` int(11) NULL DEFAULT NULL,
  `rtspUrl` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'rtsp地址',
  `uploadDate` datetime(0) NULL DEFAULT NULL COMMENT '上送时间',
  `apkVersion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'APK版本',
  `imeiNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IMEI号',
  `systemVersionInfo` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统版本信息',
  `systemCompileTime` datetime(0) NULL DEFAULT NULL COMMENT '系统编译时间',
  `ICCID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ICCID',
  `IMSI` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IMSI',
  `ZHYirVersion` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ZHYir版本',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `videotime` int(11) NULL DEFAULT NULL COMMENT '视频时长970使用',
  `A200Version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'A200版本',
  `function` bigint(20) NOT NULL DEFAULT -1 COMMENT '设备支持的功能，云台上下左右，预置位，巡航线等。。。',
  `video_enable` int(11) NOT NULL DEFAULT 0 COMMENT '该设备是否支持实时视频（在客户端设备树上展示），0：不支持；1：支持，默认不支持。注意：设备类型如果选择视频设备，那么必定在客户端设备树上展示。',
  `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `old_device_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老的设备编号',
  `old_device_model_id` int(11) NULL DEFAULT NULL COMMENT '老的设备型号Id',
  `oper_user_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作人',
  `oper_user_ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人ip',
  `oper_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '操作类型，A/增加，M/修改，D/删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `access_log_id` int(11) NULL DEFAULT NULL COMMENT '对应conf.accessLog表',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
