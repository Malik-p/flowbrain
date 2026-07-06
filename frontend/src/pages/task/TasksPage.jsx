import { useState } from "react";

import MainLayout from "@/components/layout/MainLayout";
import TaskCard from "@/components/task/TaskCard";
import CreateTaskDialog from "@/components/task/CreateTaskDialog";

import { Button } from "@/components/ui/button";

import { Plus, ClipboardList } from "lucide-react";

import { useTasks } from "@/hooks/useTask";

export default function TasksPage() {

    const [open, setOpen] = useState(false);

    const { data, isLoading, isError } = useTasks();

    if (isLoading) {

        return (

            <MainLayout>

                Loading Tasks...

            </MainLayout>

        );

    }

    if (isError) {

        return (

            <MainLayout>

                Failed to load tasks.

            </MainLayout>

        );

    }

    const tasks = data?.data || [];

    const project = JSON.parse(

        localStorage.getItem("selectedProject")

    );

    return (

        <MainLayout>

            <div className="flex justify-between items-center">

                <div>

                    <h1 className="text-4xl font-bold">

                        {project?.name}

                    </h1>

                    <p className="text-slate-400 mt-2">

                        Manage project tasks

                    </p>

                </div>

                <Button

                    onClick={() => setOpen(true)}

                    className="bg-violet-600"

                >

                    <Plus className="mr-2" size={18} />

                    New Task

                </Button>

            </div>

            {

                tasks.length === 0 ?

                (

                    <div className="mt-16 flex flex-col items-center rounded-3xl border border-dashed border-[#33334A] p-16">

                        <ClipboardList

                            size={70}

                            className="text-violet-500"

                        />

                        <h2 className="text-3xl font-bold mt-6">

                            No Tasks Yet

                        </h2>

                        <Button

                            className="mt-8 bg-violet-600"

                            onClick={() => setOpen(true)}

                        >

                            Create First Task

                        </Button>

                    </div>

                )

                :

                (

                    <div className="grid xl:grid-cols-2 gap-6 mt-10">

                        {

                            tasks.map(task => (

                                <TaskCard

                                    key={task.id}

                                    task={task}

                                />

                            ))

                        }

                    </div>

                )

            }

            <CreateTaskDialog

                open={open}

                setOpen={setOpen}

            />

        </MainLayout>

    );

}