import "./planCard.less";

export default function PlanCardGrid({children}: { children: React.ReactNode | React.ReactNode[]}) {
    return (
        <div className="planCardGrid">
            {children}
        </div>
    );
}