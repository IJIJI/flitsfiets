import React from "react";
// import MainFooter from "./footer/MainFooter.tsx";
import './base.less'
import {Outlet} from "react-router";


export default function Base({children, outletEnable = true}: { children?: React.ReactNode | React.ReactNode[], outletEnable?: boolean, shadow?: boolean }) {

    return (
        <>
            {children}
            {outletEnable &&
                <Outlet/>
            }
            {/*<MainFooter />*/}
        </>
    );
}