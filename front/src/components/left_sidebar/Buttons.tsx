import React, {useState} from "react";
import {AiOutlineDelete, AiOutlineFileAdd, AiOutlineFolderAdd} from "react-icons/ai";
import {useRecoilState, useRecoilValue, useSetRecoilState} from "recoil";
import {recentSelectedFileIdAtom, recentSelectedDirectoryAtom} from "../../recoil/sidebarState";
import {filesAtom, newFileParentIDAtom} from "../../recoil/fileState";
import {FileType} from "../../common/types";
import {getFileNodeById} from "../../common/commonFunctions";

export default function Buttons() {
	const [isToolTipOpened, setIsToolTipOpened] = useState(false);
	const recentSelectedFileId = useRecoilValue(recentSelectedFileIdAtom);
	const recentSelectedDirId = useRecoilValue(recentSelectedDirectoryAtom);
	const [files, setFiles] = useRecoilState(filesAtom);
	const setNewFileParentIdAtom = useSetRecoilState(newFileParentIDAtom);

	const onClickAddFile = async (event:React.MouseEvent, fileType: FileType) => {
		event.stopPropagation();
		setNewFileParentIdAtom({
			fileType,
			padding: 0,
			parentID: recentSelectedDirId,
		});
		setIsToolTipOpened(false);
	};

	const toggleTooltip = function() {
		setIsToolTipOpened(prevState => !prevState);
	};

	const onClickDeleteFile = async () => {
		setIsToolTipOpened(false);
		if (recentSelectedFileId === "" || parseInt(recentSelectedFileId, 10) === 0) {
			return;
		}
		const fetchPromise = await fetch(`http://localhost:8080/userDirectory/${recentSelectedFileId}`,
			{
				method: "DELETE",
			},
		);

		if (fetchPromise.ok && fetchPromise.status === 200) {
			setFiles(prev => prev.filter(value => value.getID !== recentSelectedFileId));
			return;
		}

		alert("오류가 발생했습니다.");
	};

	return <div id="left-sidebar-buttons">
		<span onClick={e => onClickAddFile(e, FileType.directory)} role={"button"}>
			<AiOutlineFolderAdd/>
		</span>

		<span id="new-file" role={"button"} onClick={toggleTooltip}>
			<AiOutlineFileAdd/>
			<div id={"new-file-tooltip"}
				style={{
					display: isToolTipOpened ? "grid" : "none",
				}}
			>
				<span onClick={e => onClickAddFile(e, FileType.document)}>
					Document
				</span>
				<span onClick={e => onClickAddFile(e, FileType.task_file)}>
					Task
				</span>
			</div>
		</span>
		<span onClick={onClickDeleteFile} role={"button"}>
			<AiOutlineDelete/>
		</span>
	</div>;
}
