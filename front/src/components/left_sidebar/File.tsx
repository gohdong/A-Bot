import React, {useEffect, useState} from "react";
import {VscFileCode, VscFolder, VscFolderOpened} from "react-icons/vsc";
import {useRecoilState, useSetRecoilState} from "recoil";
import {DiMarkdown} from "react-icons/di";

import {recentSelected, recentSelectedDirectoryAtom} from "../../recoil/sidebarState";
import FileTreeNode from "../../data/Tree";
import {FileTreeViewType, FileType} from "../../common/types";
import NewFile from "./NewFile";
import {filesAtom, newFileParentIDAtom} from "../../recoil/fileState";


export default function File({fileNode, padding}: FileTreeViewType) {
	const [openChildren, setOpenChildren] = useState(false);
	const [recentSelectedFile, setRecentSelectedFile] = useRecoilState(recentSelected);
	const [newFileParentID, setNewFileParentID] = useRecoilState(newFileParentIDAtom);
	const setRecentSelectedDir = useSetRecoilState(recentSelectedDirectoryAtom);
	const [files, setFiles] = useRecoilState(filesAtom);

	useEffect(() => {
		if (fileNode.getID === newFileParentID.parentID) {
			setOpenChildren(true);
			document.getElementById("newFileName")?.focus();
		}
	}, [newFileParentID]);

	const onClickFile = (e: React.MouseEvent) => {
		e.stopPropagation();
		setRecentSelectedFile(fileNode.getID);

		if (fileNode.getFileType === FileType.directory) {
			setRecentSelectedDir(fileNode.getID);
		}

		if (recentSelectedFile === fileNode.getID && fileNode.getFileType === FileType.directory) {
			setOpenChildren(prevState => !prevState);
		}
	};

	const getIcon = function() {
		if (fileNode.getFileType === FileType.task_file) {
			return <VscFileCode/>;
		}
		if (fileNode.getFileType === FileType.document) {
			return <DiMarkdown />;
		}
		if (openChildren) {
			return <VscFolderOpened/>;
		}
		return <VscFolder/>;
	};

	return <>
		<span className={`fileTreeNodeSpan ${recentSelectedFile === fileNode.getID && "selected"}`} style={{paddingLeft: padding}} onClick={onClickFile}>
			{
				getIcon()
			}
			<p>{fileNode.getFileName}</p>
		</span>

		{
			fileNode.getFileType === FileType.directory &&
			openChildren &&
			files.filter(value => value.getParent?.getID === fileNode.getID)
				.map(value =>
					<File
						key={`dir_${value.getID}`}
						fileNode={value}
						padding={padding + 20}
					/>)
		}
		{
			newFileParentID.parentID === fileNode.getID &&
			<NewFile
				fileType={newFileParentID.fileType}
				padding={padding + 20}
				parentID={newFileParentID.parentID}
			/>
		}
	</>;
}
