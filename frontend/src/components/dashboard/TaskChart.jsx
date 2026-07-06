import {
    PieChart,
    Pie,
    Tooltip,
    ResponsiveContainer,
    Cell
} from "recharts";

const COLORS = [
    "#22C55E",
    "#F59E0B",
    "#EF4444"
];

export default function TaskChart({ dashboard }) {

    const data = [

        {
            name: "Completed",
            value: dashboard.completedTasks
        },

        {
            name: "In Progress",
            value: dashboard.inProgressTasks
        },

        {
            name: "Todo",
            value: dashboard.todoTasks
        }

    ];

    return (

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-6 h-[380px]">

            <h2 className="text-xl font-semibold mb-4">

                Task Distribution

            </h2>

            <ResponsiveContainer>

                <PieChart>

                    <Pie

                        data={data}

                        dataKey="value"

                        innerRadius={70}

                        outerRadius={110}

                    >

                        {data.map((entry, index) => (

                            <Cell
                                key={index}
                                fill={COLORS[index]}
                            />

                        ))}

                    </Pie>

                    <Tooltip />

                </PieChart>

            </ResponsiveContainer>

        </div>

    );

}