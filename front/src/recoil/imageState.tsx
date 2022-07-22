import {atom} from "recoil";
import FileTreeNode from "../data/Tree";

// eslint-disable-next-line import/prefer-default-export
export const filesState = atom<FileTreeNode[]>({
	key: "fileTree",
	default: [],
});
