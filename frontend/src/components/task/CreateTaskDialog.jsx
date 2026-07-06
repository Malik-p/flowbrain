import { useState } from "react";
import { toast } from "sonner";

import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle
} from "@/components/ui/dialog";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

import { useCreateTask } from "@/hooks/useTask";
import { useMembers } from "@/hooks/useMember";

export default function CreateTaskDialog({

    open,

    setOpen

}) {

    const { mutate, isPending } = useCreateTask();

    const { data: membersData } = useMembers();

    const members = membersData?.data || [];

    const [form, setForm] = useState({

        title: "",

        description: "",

        priority: "MEDIUM",

        dueDate: "",

        assignedUserId: ""

    });

    function createTask() {

        console.log(form);

        mutate(form, {

            onSuccess: (res) => {

                toast.success(res.message);

                setForm({

                    title: "",

                    description: "",

                    priority: "MEDIUM",

                    dueDate: "",

                    assignedUserId: ""

                });

                setOpen(false);

            },

            onError: (err) => {

                toast.error(

                    err.response?.data?.message ||

                    "Unable to create task"

                );

            }

        });

    }

    return (

        <Dialog

            open={open}

            onOpenChange={setOpen}

        >

            <DialogContent>

                <DialogHeader>

                    <DialogTitle>

                        Create Task

                    </DialogTitle>

                </DialogHeader>

                <div className="space-y-4 mt-5">

                    <Input
                        placeholder="Task Title"
                        value={form.title}
                        onChange={(e)=>
                            setForm({
                                ...form,
                                title:e.target.value
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

                    <select
                        className="w-full h-11 rounded-lg bg-[#171722] border border-[#303044] px-3"
                        value={form.priority}
                        onChange={(e)=>
                            setForm({
                                ...form,
                                priority:e.target.value
                            })
                        }
                    >

                        <option value="LOW">LOW</option>
                        <option value="MEDIUM">MEDIUM</option>
                        <option value="HIGH">HIGH</option>

                    </select>

                    <Input
                        type="datetime-local"
                        value={form.dueDate}
                        onChange={(e)=>
                            setForm({
                                ...form,
                                dueDate:e.target.value
                            })
                        }
                    />

                    <select
                        className="w-full h-11 rounded-lg bg-[#171722] border border-[#303044] px-3"
                        value={form.assignedUserId}
                        onChange={(e)=>
                            setForm({
                                ...form,
                                assignedUserId:e.target.value
                            })
                        }
                    >

                        <option value="">

                            Select Member

                        </option>

                        {

                            members.map(member=>(

                                <option
                                    key={member.id}
                                    value={member.id}
                                >

                                    {member.name}

                                </option>

                            ))

                        }

                    </select>

                    <Button

                        className="w-full bg-violet-600"

                        onClick={createTask}

                        disabled={isPending}

                    >

                        {

                            isPending

                            ? "Creating..."

                            : "Create Task"

                        }

                    </Button>

                </div>

            </DialogContent>

        </Dialog>

    );

}