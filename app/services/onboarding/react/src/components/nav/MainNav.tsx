import {useEffect, useRef, useState} from "react";
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler} from "reactstrap";
import {NavLink as RRNavLink} from "react-router";
import AccountNav from "./AccountNav.tsx";

import './nav.less';
import Logo from "../logo/Logo.tsx";


interface NavProps {
    children?: string |  React.ReactNode | React.ReactNode[];
    sticky?: boolean;
    shadow?: boolean;
    hideLogo?: boolean;
    id?: string;
}


export default function MainNav(props: NavProps) {
    const [collapsed, setCollapsed] = useState(true);
    const navbarRef = useRef<any>(null);
    let observer: IntersectionObserver | undefined;
    let interceptEl: HTMLElement | undefined;

    useEffect(() => {
        if (!props.sticky || observer) return;
        const nav = navbarRef.current;
        if (!nav) return;

        interceptEl = document.createElement('div');


        nav.parentElement.insertBefore(interceptEl, nav);

        observer = new IntersectionObserver(([entry]) => {
            nav.classList.toggle('sticky-active', !entry.isIntersecting);
        });

        observer.observe(interceptEl);


        return(() => {
            observer?.disconnect();
            observer = undefined;

            if (interceptEl) {
                interceptEl.remove();
                interceptEl = undefined;
            }

            nav?.classList.remove('sticky-active');
        });

    }, [navbarRef])

    const toggleNavbar = () => {
        setCollapsed(!collapsed);
    }

    return (
        <Navbar
            dark
            expand="lg"
            id={props.id}
            sticky={props.sticky ? "top" : undefined}
            className={props.shadow ? "active" : ""}
            ref={navbarRef}
        >

            <NavbarBrand to="/" tag={RRNavLink} className={props.hideLogo ? "hidden" : ""}><Logo /></NavbarBrand>

            <NavbarToggler onClick={toggleNavbar}/>
            <Collapse isOpen={!collapsed} navbar>
                <Nav className="mr-auto flex-grow-1" navbar>
                    {props.children}
                </Nav>
                <Nav className="/account" navbar>
                    <AccountNav/>
                </Nav>
            </Collapse>
        </Navbar>
    );

}