import {atom} from "recoil";
import FileTreeNode from "../data/Tree";
import {FileType, NewFileType} from "../common/types";

export const filesAtom = atom<FileTreeNode[]>({
	key: "fileTree",
	default: [],
});

export const newFileParentIDAtom = atom<NewFileType>({
	key: "newFileParentID",
	default: {
		parentID: "",
		fileType: FileType.directory,
		padding: 0,
	},
});
