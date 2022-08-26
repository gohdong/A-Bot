import ReactMarkdown from "react-markdown";
import React, {useCallback, useEffect, useState} from "react";
import {useRecoilState, useRecoilValue} from "recoil";
import "./MainContents.scss";
import {bigCategory, categoryToKR, color, colorCode, colorToKR, gender, smallCategory} from "./category";
import {loadingSate} from "../../recoil/loadingState";

const hash = require("object-hash");


export default function MainContents() {
	const [text, setText] = useState("");

	const onChangeText = function(e:React.FormEvent<HTMLTextAreaElement>) {
		setText(e.currentTarget.value);
	};

	return (
		<div id="main-contents">
			<div id="editor">
				<textarea
					value={text}
					onChange={onChangeText}
				/>
			</div>
			<div id="preview"></div>
			<ReactMarkdown children={text}/>
		</div>
	);
}
