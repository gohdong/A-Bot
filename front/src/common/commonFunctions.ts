import FileTreeNode from "../data/Tree";

export function getFileNodeById(fileList : FileTreeNode[], id: string) : FileTreeNode | null {
	return fileList.find((value:FileTreeNode) => value.getID === id) ?? null;
}

export function test() {

}
