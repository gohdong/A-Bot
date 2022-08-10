INSERT INTO "PUBLIC"."USER_DIRECTORY" VALUES
(165737373120925, 0, 'rootDirecotry_1', 'rootDirecotry_1_desc', 'directory', '2022-07-12 11:00:01'),
(165737414739585, 0, 'rootDirecotry_2', 'rootDirecotry_2_desc', 'directory', '2022-07-12 11:13:01'),
(165737416558102, 165737414739585, 'subDirecotry_2_1', 'subDirecotry_2_1_desc', 'directory', '2022-07-13 11:14:12'),
(165737438373425, 165737416558102, 'subFile_2_1_1', '{"key":"subFile_2_1_1_task"}' FORMAT JSON, 'task_file', '2022-07-12 11:13:15'),
(165737440689305, 165737373120925, 'subFile_1_1', 'subFile_1_1_document', 'document', '2022-07-17 17:13:56');


INSERT INTO "PUBLIC"."COMMON_HTTP_HEADER" VALUES
(101, 'Host'),
(102, 'User-Agent'), (103, 'Accept'), (104, 'Accept-Encoding'), (105, 'Connection');