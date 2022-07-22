import {useRecoilValue} from "recoil";
import "./BottomStatusBar.scss";
import CheckIcon from "../icons/CheckIcon";
import EditIcon from "../icons/EditIcon";

export default function BottomStatusBar() {
	return (
		<div id="bottom-status">
			<div>
				<CheckIcon/>
			</div>
			<div>
				<EditIcon/>
			</div>
		</div>
	);
}
