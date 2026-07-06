import { Trash2 } from "lucide-react";
import { Button } from "@/components/ui/button";
import { useDeleteProject } from "@/hooks/useProject";
import { toast } from "sonner";

export default function ProjectCard({ project }) {

    const { mutate } = useDeleteProject();

   function openProject() {

    localStorage.setItem(

        "selectedProject",

        JSON.stringify(project)

    );

    window.location.href="/tasks";

}

function removeProject(e) {

    e.stopPropagation();

    mutate(project.id,{

        onSuccess:(res)=>{

            toast.success(res.message);

        }

    });

}

    return (

        <div

    onClick={openProject}

    className="rounded-3xl border border-[#262638] bg-[#171722] p-6 cursor-pointer hover:border-violet-500 hover:scale-[1.01] transition-all"

>

            <div className="flex justify-between">

                <div>

                    <div className="flex items-center gap-3">

                        <div
                            className="h-4 w-4 rounded-full"
                            style={{
                                background: project.color
                            }}
                        />

                        <h2 className="text-2xl font-bold">

                            {project.name}

                        </h2>

                    </div>

                    <p className="text-slate-400 mt-3">

                        {project.description}

                    </p>

                    <div className="mt-5 flex gap-8 text-sm text-slate-500">

                        <span>

                            Members : {project.totalMembers}

                        </span>

                        <span>

                            {new Date(project.createdAt).toLocaleDateString()}

                        </span>

                    </div>

                </div>

                <Button

                    variant="destructive"

                    onClick={removeProject}

                >

                    <Trash2 size={18} />

                </Button>

            </div>

        </div>

    );

}