import React from "react";
// import MainFooter from "./footer/MainFooter.tsx";
import './base.less'
import {Outlet} from "react-router";
import MainNav from "./nav/MainNav.tsx";


export default function Base({children, outletEnable = true, shadow, hideLogo}: { children?: React.ReactNode | React.ReactNode[], outletEnable?: boolean, shadow?: boolean, hideLogo?: boolean }) {

    return (
        <>
            <MainNav id={"mainNav"} sticky={true} shadow={shadow} hideLogo={hideLogo}>
            </MainNav>
            {children}
            {outletEnable &&
                <Outlet/>
            }
            {/*<MainFooter />*/}
        </>
    );
}