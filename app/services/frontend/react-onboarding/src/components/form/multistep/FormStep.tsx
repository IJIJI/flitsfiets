

export default function FormStep({children}: { children: React.ReactNode | React.ReactNode[]}) {

    return (
        <>
            <div className="form-step d-flex flex-column">
                {children}
            </div>
        </>
    )

}