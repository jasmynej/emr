import Visit from "../models/Visit.js";
function VisitCard(visit){
    let v = Visit.fromJson(visit.visit)
    return (
        <div className="bg-white w-1/5 m-3 p-3 rounded">
            <p className="text-lg">{v.date}</p>
            <p>{v.reason}</p>
            <p className="font-thin">{v.notes}</p>
            {v.isUpcoming() && <p className="p-2 bg-yellow-400 text-center rounded">Upcoming</p>}

        </div>
    )
}

export default VisitCard;