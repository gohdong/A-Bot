import MDEditor, {commands} from "@uiw/react-md-editor";
import React, {useState} from "react";
import ReactMarkdown from "react-markdown";

export default function Markdown() {
	const [text, setText] = useState("");

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
				// commands={[
				// 	// Custom Toolbars
				// 	// eslint-disable-next-line max-len
				// 	commands.group([commands.title1, commands.title2, commands.title3, commands.title4, commands.title5, commands.title6], {
				// 		name: "title",
				// 		groupName: "title",
				// 		buttonProps: {"aria-label": "Insert title"},
				// 	}), commands.divider,
				// ]}

			/>
		</div>
		{/* <div id="preview">*/}
		{/*	<ReactMarkdown children={text}/>*/}
		{/* </div>*/}
	</div>;
}
