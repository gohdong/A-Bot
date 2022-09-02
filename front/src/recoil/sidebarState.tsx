import {atom} from "recoil";
import {FileType} from "../common/types";

export const sidebarState = atom({
	key: "sidebarState",
	default: true,
});

export const recentSelectedFileIdAtom = atom({
	key: "recentSelectedFileId",
	default: "",
});

export const recentSelectedFileTypeAtom = atom<FileType>({
	key: "recentSelectedFileType",
	default: FileType.directory,
});

export const recentSelectedDirectoryAtom = atom({
	key: "recentSelectedDirectory",
	default: "",
});
