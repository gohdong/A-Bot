import {useRecoilState, useRecoilValue, useSetRecoilState} from "recoil";
import "./AppBar.scss";
import ViewSideBar from "../icons/ViewSideBarIcon";
import {recentSelectedFileTypeAtom, sidebarState} from "../../recoil/sidebarState";
import ExportIcon from "../icons/ExportIcon";
import {isMarkdownEditingAtom} from "../../recoil/fileState";

function AppBar() {
	const setSidebarOpen = useSetRecoilState(sidebarState);
	const [isMarkdownEditing, setIsMarkdownEditing] = useRecoilState(isMarkdownEditingAtom);
	// eslint-disable-next-line max-len
	const [recentSelectedFileType, setRecentSelectedFileType] = useRecoilState(recentSelectedFileTypeAtom);

	const onClickButton = () => {
		setSidebarOpen(prev => !prev);
	};

	return (
		<div id={"app-bar"}>
			<div className="app-bar-wrap">
				<p>A-Bot</p>
			</div>
			{
				isMarkdownEditing ?
					<span className={"button save"}>저장</span> :
					<span className={"button edit"}>수정</span>
			}


		</div>
	);
}

export default AppBar;
