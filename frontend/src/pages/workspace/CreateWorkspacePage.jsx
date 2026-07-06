import { useState } from "react";
import { useNavigate } from "react-router-dom";

import MainLayout from "@/components/layout/MainLayout";

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

import { createWorkspace } from "@/services/workspaceService";

import { toast } from "sonner";

export default function CreateWorkspacePage() {

    const navigate = useNavigate();

    const [loading, setLoading] = useState(false);

    const [form, setForm] = useState({

        name: "",

        description: ""

    });

    async function submit() {

        try {

            setLoading(true);

            const res = await createWorkspace(form);

            localStorage.setItem(

                "workspace",

                JSON.stringify(res.data)

            );

            toast.success(res.message);

            navigate("/app");

        } catch (err) {

            toast.error(

                err.response?.data?.message ||

                "Unable to create workspace"

            );

        } finally {

            setLoading(false);

        }

    }

    return (

        <MainLayout>

            <div className="max-w-xl mx-auto mt-20 rounded-3xl bg-[#171722] border border-[#262638] p-10">

                <h1 className="text-4xl font-bold">

                    Create Your Workspace

                </h1>

                <p className="text-slate-400 mt-3">

                    Create your company workspace to get started.

                </p>

                <div className="space-y-5 mt-10">

                    <Input

                        placeholder="Workspace Name"

                        value={form.name}

                        onChange={(e) => setForm({

                            ...form,

                            name: e.target.value

                        })}

                    />

                    <Input

                        placeholder="Description"

                        value={form.description}

                        onChange={(e) => setForm({

                            ...form,

                            description: e.target.value

                        })}

                    />

                    <Button

                        onClick={submit}

                        disabled={loading}

                        className="w-full bg-violet-600"

                    >

                        {

                            loading

                                ?

                                "Creating..."

                                :

                                "Create Workspace"

                        }

                    </Button>

                </div>

            </div>

        </MainLayout>

    );

}