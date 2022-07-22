import {useState} from "react";
import {VscFileCode, VscFolder, VscFolderOpened} from "react-icons/vsc";

import FileTreeNode, {FileType} from "../../data/Tree";

export type FileTreeViewType = {
	fileNode: FileTreeNode,
	padding: number
}

export default function File({fileNode, padding}: FileTreeViewType) {
	const [openChildren, setOpenChildren] = useState(false);
	const onClickFile = () => {
		if (fileNode.getFileType === FileType.DIRECTORY) {
			setOpenChildren(prevState => !prevState);
		} else {
			console.log("AA");
		}
	};

	const getIcon = function() {
		if (fileNode.getFileType !== FileType.DIRECTORY) {
			return <VscFileCode/>;
		}
		if (openChildren) {
			return <VscFolderOpened/>;
		}
		return <VscFolder/>;
	};

	return <>
		<span className="fileTreeNodeSpan" style={{paddingLeft: padding}} onClick={onClickFile}>
			{
				getIcon()
			}
			<p>{fileNode.getFileName}</p>
		</span>

		{
			fileNode.getFileType === FileType.DIRECTORY && openChildren &&
			fileNode.getChildren.map(value => <File key={`dir_${value.getID}`} fileNode={value}
				padding={padding + 20}/>)
		}
	</>;
}
