import "./bikeCard.less";

export default function BikeCardGrid({children}: { children: React.ReactNode | React.ReactNode[]}) {
    return (
        <div className="cardGrid">
            {children}
        </div>
    );
}