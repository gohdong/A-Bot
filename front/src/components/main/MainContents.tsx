import React, {useCallback, useEffect, useState} from "react";
import {useRecoilState, useRecoilValue} from "recoil";
import "./MainContents.scss";
import {bigCategory, categoryToKR, color, colorCode, colorToKR, gender, smallCategory} from "./category";
import {loadingSate} from "../../recoil/loadingState";

const hash = require("object-hash");


export default function MainContents() {
	return (
		<div id="main-contents">
			{/* <div id="tab-bar">*/}
			{/*	<div className="tab selected">asd</div>*/}
			{/*	<div className="tab">asd</div>*/}
			{/*	<div className="tab">asd</div>*/}
			{/*	<div className="tab">asd</div>*/}
			{/* </div>*/}
		</div>
	);
}
