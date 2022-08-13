import React, {useEffect} from "react";
import {useRecoilState, useSetRecoilState} from "recoil";
import "./LeftSidebar.scss";
import FileTreeNode from "../../data/Tree";
import {filesAtom, newFileParentIDAtom} from "../../recoil/fileState";
import {getFileNodeById} from "../../common/commonFunctions";
import {file, FileType} from "../../common/types";
import File from "./File";
import Buttons from "./Buttons";
import {recentSelected} from "../../recoil/sidebarState";


export default function LeftSidebar() {
	const [files, setFiles] = useRecoilState(filesAtom);
	const [recentSelectedFile, setRecentSelectedFile] = useRecoilState(recentSelected);
	const setNewFileParentID = useSetRecoilState(newFileParentIDAtom);

	useEffect(() => {
		async function fetchData() {
			const fetchPromise = await fetch("http://localhost:8080/userDirectory", {method: "GET"});
			const data = await fetchPromise.json();

			return data;
		}

		fetchData().then((value:[]) => {
			const result: FileTreeNode[] = [];

			value.forEach((value1: file) => {
				const tempFileNode = new FileTreeNode(
					value1?.name,
					value1.id.toString(),
					getFileNodeById(result, value1.parentId.toString()),
					FileType[value1.fileType as unknown as keyof typeof FileType],
				);

				result.push(tempFileNode);
			});
			setFiles(currVal => [...currVal, ...result]);
		});
	}, []);

	const onClickFileTreeWrap = (e:React.MouseEvent) => {
		setRecentSelectedFile("0");
		setNewFileParentID({
			padding: 0,
			fileType: FileType.directory,
			parentID: "",
		});
	};

	return (
		<div id="left-sidebar">
			<div id="fileTreeWrap" onClick={onClickFileTreeWrap}>
				{
					files
						.filter(value => value.getParent === null)
						.map(value => <File key={`${value.getID}`} fileNode={value} padding={0}/>)
				}
			</div>
			<Buttons />
		</div>
	);
}
