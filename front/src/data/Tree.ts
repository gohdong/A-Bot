export enum FileType{
    DIRECTORY,
    DOCUMENT,
    TASK
}

export default class FileTreeNode {
	// eslint-disable-next-line no-use-before-define
	private children: FileTreeNode[];
	private fileName;
	private id;
	// eslint-disable-next-line no-use-before-define
	private parent: FileTreeNode | null;
	private fileType: FileType = 0;


	get getID() {
		return this.id;
	}

	get getChildren() {
		return this.children;
	}

	get getFileName(): string {
		return this.fileName;
	}

	get getParent() {
		return this.parent;
	}

	get getFileType() {
		return this.fileType;
	}

	constructor(fileName: string, id: string, parent:FileTreeNode | null, fileType:FileType) {
		this.fileName = fileName;
		this.id = id;
		this.children = [];
		this.parent = parent;
		this.parent?.addDescendants(this);
		this.fileType = fileType;
	}

	addDescendants(node: FileTreeNode) {
		this.children.push(node);
	}

	allChildren() {
		let result: FileTreeNode[] = [];

		this.children.forEach((value, index) => {
			result.push(value);
			result = [...result, ...value.allChildren()];
		});

		return result;
	}
}
