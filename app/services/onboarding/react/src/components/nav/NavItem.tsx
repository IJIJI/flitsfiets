import {NavItem as RNavItem, NavLink} from "reactstrap";
import {NavLink as RRNavLink} from "react-router";
import React, {type JSX} from "react";

import 'bootstrap/dist/css/bootstrap.min.css';
import './navItem.less';

export interface NavItemProps {
    path: string;
    label: string;
    icon: JSX.Element;
}

class NavItem extends React.Component<NavItemProps> {
    render() {
        return (
            <RNavItem>
                <NavLink to={this.props.path} tag={RRNavLink}>{this.props.icon}{this.props.label}</NavLink>
            </RNavItem>
        );
    }
}

export default NavItem;