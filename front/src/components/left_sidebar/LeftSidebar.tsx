import React, {useEffect} from "react";
import {useRecoilState} from "recoil";
import "./LeftSidebar.scss";
import FileTreeNode, {FileType} from "../../data/Tree";
import {filesState} from "../../recoil/imageState";
import {getFileNodeById} from "../../common/commonFunctions";
import {file} from "../../common/types";
import File from "./File";


export default function LeftSidebar() {
	const [files, setFiles] = useRecoilState(filesState);

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
					value1.isFile ? FileType.TASK : FileType.DIRECTORY,
				);

				result.push(tempFileNode);
			});
			setFiles(currVal => [...currVal, ...result]);
		});
	}, []);

	return (
		<div id="left-sidebar">
			<div id="fileTreeWrap">
				{
					files
						.filter(value => value.getParent === null)
						.map(value => <File key={`dir_${value.getID}`} fileNode={value} padding={0}/>)
				}
			</div>
			<div id="left-sidebar-buttons">
			</div>
		</div>
	);
}
