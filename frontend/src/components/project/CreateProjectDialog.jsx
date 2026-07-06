import { useState } from "react";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

import { toast } from "sonner";

import { useCreateProject } from "@/hooks/useProject";

export default function CreateProjectDialog({

    open,

    setOpen

}) {

    const { mutate } = useCreateProject();

    const [form, setForm] = useState({

        name: "",

        description: "",

        color: "#8B5CF6"

    });

    if (!open) return null;

    function submit() {

        mutate(form, {

            onSuccess: (res) => {

                toast.success(res.message);

                setOpen(false);

                setForm({

                    name: "",

                    description: "",

                    color: "#8B5CF6"

                });

            }

        });

    }

    return (

        <div className="fixed inset-0 bg-black/60 flex justify-center items-center z-50">

            <div className="w-[520px] rounded-3xl bg-[#171722] border border-[#2A2A3D] p-8">

                <h2 className="text-3xl font-bold">

                    New Project

                </h2>

                <div className="space-y-5 mt-8">

                    <Input

                        placeholder="Project Name"

                        value={form.name}

                        onChange={(e) =>

                            setForm({

                                ...form,

                                name: e.target.value

                            })

                        }

                    />

                    <Input

                        placeholder="Description"

                        value={form.description}

                        onChange={(e) =>

                            setForm({

                                ...form,

                                description: e.target.value

                            })

                        }

                    />

                    <Input

                        type="color"

                        value={form.color}

                        onChange={(e) =>

                            setForm({

                                ...form,

                                color: e.target.value

                            })

                        }

                    />

                    <div className="flex justify-end gap-3">

                        <Button

                            variant="outline"

                            onClick={() => setOpen(false)}

                        >

                            Cancel

                        </Button>

                        <Button

                            onClick={submit}

                            className="bg-violet-600"

                        >

                            Create

                        </Button>

                    </div>

                </div>

            </div>

        </div>

    );

}