import "./planCard.less";
import "./../card.less";


export default function PlanCard({index, setIndex, activeIndex, title, price, discountedprice}: {
    index: number | string,
    setIndex: (index: number | string) => void,
    activeIndex: number | string,
    title: string,
    price: string,
    discountedprice?: string
}) {
    return (
        <button className={"card planCard " + (activeIndex === index ? "active" : "")} onClick={() => setIndex(index)}>
            <h3>{title}</h3>

            {discountedprice == null ?
                <div className="priceDiv">
                    <span className="original">
                        {price}
                    </span>
                </div>
                :

                <div className={"priceDiv discounted"}>
                    <span className="original">
                        {price}
                    </span>
                    <span className="discounted">
                        {discountedprice}
                    </span>
                </div>
            }
        </button>
    )
}