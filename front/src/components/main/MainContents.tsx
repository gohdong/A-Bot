import React, {useCallback, useEffect, useState} from "react";
import "./MainContents.scss";
import Markdown from "./Markdown";


export default function MainContents() {
	return (
		<div id="main-contents">
			<Markdown />
		</div>
	);
}
