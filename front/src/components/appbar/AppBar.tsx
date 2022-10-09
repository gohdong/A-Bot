import {useRecoilState, useRecoilValue} from "recoil";
import {useState} from "react";
import "./AppBar.scss";
import {recentSelectedFileIdAtom, recentSelectedFileTypeAtom} from "../../recoil/sidebarState";
import {filesAtom, isMarkdownEditingAtom, markDownValueAtom} from "../../recoil/fileState";
import {documentType, FileType, taskFileType} from "../../common/types";
import {getFileNodeById} from "../../common/commonFunctions";

function AppBar() {
	const [isMarkdownEditing, setIsMarkdownEditing] = useRecoilState(isMarkdownEditingAtom);
	const recentSelectedFileType = useRecoilValue(recentSelectedFileTypeAtom);
	const [recentSelectedFileId, setRecentSelectedFileId] = useRecoilState(recentSelectedFileIdAtom);
	const markdownValue = useRecoilValue(markDownValueAtom);

	const onClickButton = async () => {
		setIsMarkdownEditing(prev => !prev);

		if (recentSelectedFileType === FileType.task_file && isMarkdownEditing) {
			console.log("!?");
		}

		if (recentSelectedFileType === FileType.document && isMarkdownEditing) {
			const fetchPromise = await fetch(`http://localhost:8080/userDirectoryContent/document/${recentSelectedFileId}`, {
				method: "PUT",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify({
					markdown: markdownValue,
				}),
			});

			setRecentSelectedFileId("0");
			setRecentSelectedFileId(`${await fetchPromise.json()}`);
		}
	};

	return (
		<div id={"app-bar"}>
			<div className="app-bar-wrap">
				<p>A-Bot</p>
			</div>

			{
				recentSelectedFileType !== FileType.directory && <div onClick={onClickButton}>
					{
						isMarkdownEditing ?
							<span className={"button save"}>저장</span> :
							<span className={"button edit"}>수정</span>}
				</div>
			}
		</div>
	);
}

export default AppBar;
