import {
    FolderKanban,
    CheckSquare,
    Briefcase
} from "lucide-react";

export default function ActivityTimeline() {

    const workspace=JSON.parse(

        localStorage.getItem("workspace")

    );

    const project=JSON.parse(

        localStorage.getItem("selectedProject")

    );

    return(

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

            <h2 className="text-2xl font-bold">

                Activity Timeline

            </h2>

            <div className="mt-8 space-y-6">

                {

                    workspace &&

                    <div className="flex gap-4">

                        <Briefcase className="text-cyan-400"/>

                        <div>

                            <p className="font-semibold">

                                Workspace Active

                            </p>

                            <p className="text-slate-500">

                                {workspace.name}

                            </p>

                        </div>

                    </div>

                }

                {

                    project &&

                    <div className="flex gap-4">

                        <FolderKanban className="text-violet-400"/>

                        <div>

                            <p className="font-semibold">

                                Current Project

                            </p>

                            <p className="text-slate-500">

                                {project.name}

                            </p>

                        </div>

                    </div>

                }

                <div className="flex gap-4">

                    <CheckSquare className="text-green-400"/>

                    <div>

                        <p className="font-semibold">

                            Ready to Manage Tasks

                        </p>

                        <p className="text-slate-500">

                            Create, assign and track tasks.

                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}