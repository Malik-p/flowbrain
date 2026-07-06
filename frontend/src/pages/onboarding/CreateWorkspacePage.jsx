import { useState } from "react";
import { useNavigate } from "react-router-dom";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

import { createWorkspace } from "@/services/workspaceService";

import { toast } from "sonner";

export default function CreateWorkspacePage() {

    const navigate = useNavigate();

    const [form, setForm] = useState({

        name: "",

        description: ""

    });

    async function submit() {

        try {

            const response = await createWorkspace(form);

            localStorage.setItem(

                "workspace",

                JSON.stringify(response.data)

            );

            toast.success(response.message);

            navigate("/dashboard");

        } catch (e) {

            toast.error(

                e.response?.data?.message ||

                "Unable to create workspace"

            );

        }

    }

    return (

        <div className="min-h-screen bg-[#09090B] flex justify-center items-center">

            <div className="w-[520px] rounded-3xl bg-[#171722] border border-[#262638] p-10">

                <h1 className="text-4xl font-bold">

                    Welcome 👋

                </h1>

                <p className="text-slate-400 mt-2">

                    Create your first workspace.

                </p>

                <div className="space-y-5 mt-8">

                    <Input

                        placeholder="Workspace Name"

                        value={form.name}

                        onChange={(e)=>

                            setForm({

                                ...form,

                                name:e.target.value

                            })

                        }

                    />

                    <Input

                        placeholder="Description"

                        value={form.description}

                        onChange={(e)=>

                            setForm({

                                ...form,

                                description:e.target.value

                            })

                        }

                    />

                    <Button

                        onClick={submit}

                        className="w-full bg-violet-600"

                    >

                        Create Workspace

                    </Button>

                </div>

            </div>

        </div>

    );

}