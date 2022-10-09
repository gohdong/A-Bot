import React, {useEffect, useState} from "react";
import {useRecoilValue, useSetRecoilState} from "recoil";
import "./MainContents.scss";
import {recentSelectedFileIdAtom, recentSelectedFileTypeAtom} from "../../recoil/sidebarState";
import {isMarkdownEditingAtom} from "../../recoil/fileState";
import MarkdownViewer from "./MarkdownViewer";
import {documentType, FileType, taskFileType} from "../../common/types";
import MarkdownEditor from "./MarkdownEditor";


export default function MainContents() {
	const [file, setFile] = useState<documentType | taskFileType | undefined>(undefined);
	const recentSelectFile = useRecoilValue(recentSelectedFileIdAtom);
	const isMarkdownEditing = useRecoilValue(isMarkdownEditingAtom);
	const setRecentSelectedFileType = useSetRecoilState(recentSelectedFileTypeAtom);

	useEffect(() => {
		async function fetchData() {
			if (recentSelectFile === "0") {
				return;
			}

			const fetching = await fetch(`http://localhost:8080/userDirectoryContent/${recentSelectFile}`);

			const data = await fetching.json();


			if (data.fileType === "document") {
				setFile({
					id: data.id,
					fileType: FileType.document,
					description: data.description,
					markdown: data.markdown !== undefined ? data.markdown : "",
				});
				setRecentSelectedFileType(FileType.document);
			} else if (data.fileType === "task_file") {
				setFile({
					id: data.id,
					fileType: FileType.task_file,
					description: data.description,
				});
				setRecentSelectedFileType(FileType.task_file);
			} else {
				setRecentSelectedFileType(FileType.directory);
			}
		}

		fetchData();
	}, [recentSelectFile]);

	const getBody = () => {
		if (file !== undefined) {
			if (file.fileType === FileType.task_file) {
				return <div></div>;
			}
			if (file.fileType === FileType.document) {
				if (isMarkdownEditing) {
					// @ts-ignore
					return <MarkdownEditor markdown={file.description}/>;
				}
				// @ts-ignore
				return <MarkdownViewer source={file.description}/>;
			}
		}
		return <p>파일을 선택해 주세요</p>;
	};

	return (
		<div id="main-contents">
			{getBody()}
		</div>
	);
}
