
import {NavItem as RNavItem, NavLink} from "reactstrap";
import {NavLink as RRNavLink} from "react-router";

import './navAccount.less';

import {BsPersonBadge} from "react-icons/bs";


export default function AccountNav() {

    return (
        <RNavItem>
            <NavLink to={"https://app.flitsfiets.nl"} className={"navAccount"} tag={RRNavLink}>
                <BsPersonBadge/>
            </NavLink>
        </RNavItem>
    );
}