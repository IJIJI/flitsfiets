import "./bikeCard.less";
import "./../card.less";
import type {JSX} from "react";


export default function BikeCard({index, setIndex, activeIndex, title, icon}: {
    index: number,
    setIndex: (index: number) => void,
    activeIndex: number,
    title: string,
    icon: JSX.Element
}) {
    return (
        <button className={"card bikeCard " + (activeIndex === index ? "active" : "")} onClick={() => setIndex(index)}>
            {icon}
            <h3>{title}</h3>
        </button>
    )
}