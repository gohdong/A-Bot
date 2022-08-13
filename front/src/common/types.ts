import FileTreeNode from "../data/Tree";

export enum FileType{
	directory,
	document,
	task_file
}

export type file = {
	id: number,
	parentId : number,
	name : string,
	description : string,
	fileType : FileType,
	isNewFile : boolean
};

export type FileTreeViewType = {
	fileNode: FileTreeNode,
	padding: number
}

export type NewFileType = {
	fileType: FileType,
	parentID: string,
	padding: number
}
