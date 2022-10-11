import {useRecoilState, useRecoilValue} from "recoil";
import "./AppBar.scss";
import {recentSelectedFileIdAtom, recentSelectedFileTypeAtom} from "../../recoil/sidebarState";
import {isMarkdownEditingAtom, markDownValueAtom} from "../../recoil/fileState";
import {FileType} from "../../common/types";

function AppBar() {
	const [isMarkdownEditing, setIsMarkdownEditing] = useRecoilState(isMarkdownEditingAtom);
	const recentSelectedFileType = useRecoilValue(recentSelectedFileTypeAtom);
	const recentSelectedFileId = useRecoilValue(recentSelectedFileIdAtom);
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

			console.log(fetchPromise);
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
