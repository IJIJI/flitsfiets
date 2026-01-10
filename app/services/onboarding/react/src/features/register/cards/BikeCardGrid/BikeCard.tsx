import "./bikeCard.less";
import "./../card.less";
import type {JSX} from "react";


export default function BikeCard({index, setIndex, activeIndex, title, description, startprice, icon}: {
    index: number | string,
    setIndex: (index: number | string) => void,
    activeIndex: number | string,
    title: string,
    description: string,
    startprice: string,
    icon: JSX.Element
}) {
    return (
        <button className={"card bikeCard " + (activeIndex === index ? "active" : "")} onClick={() => setIndex(index)}>
            {icon}
            <div className="infostack">
                <h3>{title}</h3>
                <p>{description}</p>
                <small>Starting at <span>{startprice}</span></small>
            </div>
        </button>
    )
}