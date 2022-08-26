import React from "react";
import {useRecoilState} from "recoil";
import "./App.scss";
import AppBar from "./components/appbar/AppBar";
import LeftSidebar from "./components/left_sidebar/LeftSidebar";
import BottomStatusBar from "./components/bottom_status_bar/BottomStatusBar";
import MainContents from "./components/main/MainContents";

function App() {
	return (
		<>
			<AppBar/>
			<div id="main-wrapper">
				<div id="contents-wrapper">
					<div id="static-contents">
						<div className="flex-wrapper">
							<LeftSidebar/>
							<MainContents/>
						</div>
					</div>
				</div>
			</div>
			<BottomStatusBar/>
		</>
	);
}

export default App;
