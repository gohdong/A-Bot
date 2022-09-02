import MDEditor, {commands} from "@uiw/react-md-editor";
import React, {useEffect, useState} from "react";
import ReactMarkdown from "react-markdown";
import {useRecoilState} from "recoil";
import {markDownValueAtom} from "../../recoil/fileState";

type markdownEditType = {
	markdown : string
}

export default function MarkdownEditor({markdown} : markdownEditType) {
	const [text, setText] = useRecoilState(markDownValueAtom);

	useEffect(() => {
		setText("");
	},
	[]);

	const onChangeText = function(e: React.FormEvent<HTMLTextAreaElement>) {
		setText(e.currentTarget.value);
	};

	return <div id="markdown">
		<div id="editor-wrap">
			<MDEditor
				id="editor"
				value={text}
				// @ts-ignore
				onChange={setText}
				preview={"edit"}
				height={"100%"}
			/>
		</div>
		{/* <div id="preview">*/}
		{/*	<ReactMarkdown children={text}/>*/}
		{/* </div>*/}
	</div>;
}
