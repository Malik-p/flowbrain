import {
    CheckCircle2,
    Clock3,
    CircleDashed
} from "lucide-react";

import { useTasks } from "@/hooks/useTask";

export default function RecentTasks() {

    const { data, isLoading } = useTasks();

    if (isLoading) {

        return (

            <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

                Loading...

            </div>

        );

    }

    const tasks = (data?.data || [])
        .sort((a, b) =>
            new Date(b.createdAt) - new Date(a.createdAt)
        )
        .slice(0, 5);

    return (

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

            <h2 className="text-2xl font-bold">

                Recent Tasks

            </h2>

            <div className="space-y-5 mt-8">

                {

                    tasks.length === 0 ?

                    (

                        <p className="text-slate-400">

                            No tasks yet.

                        </p>

                    )

                    :

                    tasks.map(task=>{

                        let Icon=CircleDashed;

                        let color="text-violet-400";

                        if(task.status==="DONE"){

                            Icon=CheckCircle2;

                            color="text-green-400";

                        }

                        else if(task.status==="IN_PROGRESS"){

                            Icon=Clock3;

                            color="text-yellow-400";

                        }

                        return(

                            <div

                                key={task.id}

                                className="flex justify-between items-center"

                            >

                                <div className="flex items-center gap-3">

                                    <Icon

                                        size={20}

                                        className={color}

                                    />

                                    <div>

                                        <p className="font-medium">

                                            {task.title}

                                        </p>

                                        <p className="text-sm text-slate-500">

                                            {task.priority}

                                        </p>

                                    </div>

                                </div>

                                <span className="text-sm text-slate-400">

                                    {task.status}

                                </span>

                            </div>

                        );

                    })

                }

            </div>

        </div>

    );

}