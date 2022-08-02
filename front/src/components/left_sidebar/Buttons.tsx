import {json} from "stream/consumers";
import React from "react";
import {AiOutlineDelete, AiOutlineFileAdd, AiOutlineFolderAdd} from "react-icons/ai";
import {useRecoilState, useRecoilValue} from "recoil";
import {recentSelected} from "../../recoil/sidebarState";
import {filesState} from "../../recoil/imageState";

export default function Buttons() {
	const recentSelectedFileId = useRecoilValue(recentSelected);
	const [_, setFiles] = useRecoilState(filesState);

	const onClickAddFile = async (event:React.MouseEvent, isFile:boolean) => {
		const body = {
			description: "string",
			fileType: "document",
			name: "test",
			parentId: recentSelectedFileId,
		};

		const fetchPromise = await fetch("http://localhost:8080/userDirectory",
			// https://stackoverflow.com/questions/32500073/request-header-field-access-control-allow-headers-is-not-allowed-by-itself-in-pr
			{
				// mode: "cors",
				// credentials: "same-origin",
				method: "POST",
				headers: {
					"Access-Control-Allow-Origin": "*",
					"accept": "*/*",
					"Content-Type": "application/json",
				},
				body: JSON.stringify(body),
			});

		console.log(fetchPromise);
	};

	const onClickDeleteFile = async () => {
		const fetchPromise = await fetch(`http://localhost:8080/userDirectory/${recentSelectedFileId}`,
			{
				method: "DELETE",
			});

		if (fetchPromise.ok && fetchPromise.status === 200) {
			setFiles(prev => prev.filter(value => value.getID !== recentSelectedFileId));
			return;
		}

		alert("오류가 발생했습니다.");
	};

	return <div id="left-sidebar-buttons">
		<span onClick={e => onClickAddFile(e, false)} role={"button"}>
			<AiOutlineFolderAdd/>
		</span>
		<span onClick={e => onClickAddFile(e, true)} role={"button"}>
			<AiOutlineFileAdd/>
		</span>
		<span onClick={onClickDeleteFile} role={"button"}>
			<AiOutlineDelete/>
		</span>

	</div>;
}
