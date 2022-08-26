import {useRecoilValue, useSetRecoilState} from "recoil";
import "./AppBar.scss";
import ViewSideBar from "../icons/ViewSideBarIcon";
import {sidebarState} from "../../recoil/sidebarState";
import ExportIcon from "../icons/ExportIcon";

function AppBar() {
	const setSidebarOpen = useSetRecoilState(sidebarState);
	const onClickButton = () => {
		setSidebarOpen(prev => !prev);
	};

	return (
		<div id={"app-bar"}>
			<div className="app-bar-wrap">
				<p>A-Bot</p>
			</div>
		</div>
	);
}

export default AppBar;
