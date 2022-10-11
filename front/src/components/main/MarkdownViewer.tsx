import MDEditor, {commands} from "@uiw/react-md-editor";
import React, {useState} from "react";
import ReactMarkdown from "react-markdown";

export default function MarkdownViewer({source}:any) {
	const [text, setText] = useState("");

	const onChangeText = function(e: React.FormEvent<HTMLTextAreaElement>) {
		setText(e.currentTarget.value);
	};

	return <div id="markdown">
		<div id="editor-wrap">
			<MDEditor.Markdown source={source} />
		</div>
	</div>;
}
