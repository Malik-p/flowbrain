import { useState } from "react";
import { Plus, FolderOpen } from "lucide-react";

import MainLayout from "@/components/layout/MainLayout";
import ProjectCard from "@/components/project/ProjectCard";
import CreateProjectDialog from "@/components/project/CreateProjectDialog";
import { Button } from "@/components/ui/button";

import { useProjects } from "@/hooks/useProject";

export default function ProjectsPage() {

    const [open, setOpen] = useState(false);

    const { data, isLoading, isError } = useProjects();

    if (isLoading) {

        return (

            <MainLayout>

                <div className="text-white text-xl">

                    Loading Projects...

                </div>

            </MainLayout>

        );

    }

    if (isError) {

        return (

            <MainLayout>

                <div className="text-red-400 text-xl">

                    Failed to load projects.

                </div>

            </MainLayout>

        );

    }

    const projects = data?.data || [];

    return (

        <MainLayout>

            <div className="flex justify-between items-center">

                <div>

                    <h1 className="text-4xl font-bold">

                        Projects

                    </h1>

                    <p className="text-slate-400 mt-2">

                        Manage all your workspace projects.

                    </p>

                </div>

                <Button

                    onClick={() => setOpen(true)}

                    className="bg-violet-600 hover:bg-violet-700"

                >

                    <Plus className="mr-2" size={18} />

                    New Project

                </Button>

            </div>

            {

                projects.length === 0 ? (

                    <div className="mt-16 rounded-3xl border border-dashed border-[#33334A] p-16 flex flex-col items-center">

                        <FolderOpen

                            size={70}

                            className="text-violet-400"

                        />

                        <h2 className="text-3xl font-bold mt-6">

                            No Projects Yet

                        </h2>

                        <p className="text-slate-400 mt-3">

                            Create your first project to start managing tasks.

                        </p>

                        <Button

                            onClick={() => setOpen(true)}

                            className="mt-8 bg-violet-600"

                        >

                            Create First Project

                        </Button>

                    </div>

                ) : (

                    <div className="grid xl:grid-cols-2 gap-6 mt-10">

                        {

                            projects.map((project) => (

                                <ProjectCard

                                    key={project.id}

                                    project={project}

                                />

                            ))

                        }

                    </div>

                )

            }

            <CreateProjectDialog

                open={open}

                setOpen={setOpen}

            />

        </MainLayout>

    );

}