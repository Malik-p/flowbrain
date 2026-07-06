import { Trash2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import { useDeleteTask } from "@/hooks/useTask";
import { toast } from "sonner";

export default function TaskCard({ task }) {

    const { mutate } = useDeleteTask();

    function removeTask() {

        mutate(task.id,{

            onSuccess:(res)=>{

                toast.success(res.message);

            }

        });

    }

    return (

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-6">

            <div className="flex justify-between">

                <h2 className="text-2xl font-bold">

                    {task.title}

                </h2>

                <Button

                    variant="destructive"

                    onClick={removeTask}

                >

                    <Trash2 size={16}/>

                </Button>

            </div>

            <p className="text-slate-400 mt-4">

                {task.description}

            </p>

            <div className="mt-6 space-y-2 text-sm">

                <p>

                    <span className="text-slate-500">

                        Assigned :

                    </span>

                    {" "}

                    {task.assignedTo}

                </p>

                <p>

                    <span className="text-slate-500">

                        Priority :

                    </span>

                    {" "}

                    {task.priority}

                </p>

                <p>

                    <span className="text-slate-500">

                        Status :

                    </span>

                    {" "}

                    {task.status}

                </p>

                <p>

                    <span className="text-slate-500">

                        Due :

                    </span>

                    {" "}

                    {

                        new Date(task.dueDate)

                        .toLocaleDateString()

                    }

                </p>

            </div>

        </div>

    );

}