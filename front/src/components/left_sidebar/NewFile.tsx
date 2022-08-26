import React, {useState} from "react";
import {VscFileCode, VscFolder, VscFolderOpened} from "react-icons/vsc";
import {useRecoilState, useSetRecoilState} from "recoil";
import {DiMarkdown} from "react-icons/di";

import {recentSelected} from "../../recoil/sidebarState";
import FileTreeNode from "../../data/Tree";
import {FileType, NewFileType} from "../../common/types";
import {filesAtom, newFileParentIDAtom} from "../../recoil/fileState";
import {getFileNodeById} from "../../common/commonFunctions";


export default function NewFile({fileType, padding, parentID}: NewFileType) {
	const setNewFileParentID = useSetRecoilState(newFileParentIDAtom);
	const [files, setFiles] = useRecoilState(filesAtom);

	const getIcon = function() {
		if (fileType === FileType.task_file) {
			return <VscFileCode/>;
		}
		if (fileType === FileType.document) {
			return <DiMarkdown />;
		}
		return <VscFolder/>;
	};

	const onKeyPress = async function(event:React.KeyboardEvent) {
		if (event.key === "Enter") {
			const body = {
				description: "string",
				fileType: FileType[fileType],
				// @ts-ignore
				name: event.target.value,
				parentId: parseInt(parentID, 10),
			};

			const fetchPromise = await fetch(`http://localhost:8080/userDirectory`,
				{
					method: "POST",
					headers: {
						"Content-Type": "application/json",
					},
					body: JSON.stringify(body),
				},
			);


			if (fetchPromise.ok && fetchPromise.status === 200) {
				const id = await fetchPromise.json();
				const parentNode = getFileNodeById(files, parentID);

				const newFileNode = new FileTreeNode(
					// @ts-ignore
					event.target.value,
					id.toString(),
					parentNode,
					fileType,
				);

				setFiles(prev => [...prev, newFileNode]);
			}
		}

		if (event.key === "Escape" || event.key === "Enter") {
			setNewFileParentID({
				padding: 0,
				fileType: FileType.directory,
				parentID: "",
			});
		}
	};

	const onClick = function(e:React.MouseEvent) {
		e.stopPropagation();
	};

	return <>
		<span id="asd" className={`fileTreeNodeSpan`} onClick={onClick} style={{paddingLeft: padding}}>
			{
				getIcon()
			}
			<input onKeyDown={onKeyPress} id={"newFileName"} type={"text"}/>
		</span>
	</>;
}
