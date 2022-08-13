import {atom} from "recoil";

export const sidebarState = atom({
	key: "sidebarState",
	default: true,
});

export const recentSelected = atom({
	key: "recentSelected",
	default: "",
});

export const recentSelectedDirectoryAtom = atom({
	key: "recentSelectedDirectory",
	default: "",
});
