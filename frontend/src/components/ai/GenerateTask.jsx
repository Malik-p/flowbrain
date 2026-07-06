import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Sparkles } from "lucide-react";
import { useGenerateTask } from "@/hooks/useAI";

export default function GenerateTask() {

    const [prompt, setPrompt] = useState("");

    const { mutate, isPending, data } = useGenerateTask();

    function generate() {

        if (!prompt.trim()) return;

        mutate({

            prompt

        });

    }

    const tasks = data?.data?.tasks || [];

    return (

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

            <div className="flex items-center gap-3">

                <Sparkles className="text-violet-400"/>

                <h2 className="text-2xl font-bold">

                    AI Task Generator

                </h2>

            </div>

            <Input

                className="mt-6"

                placeholder="Example: Build Authentication Module"

                value={prompt}

                onChange={(e)=>setPrompt(e.target.value)}

            />

            <Button

                className="mt-5 bg-violet-600"

                onClick={generate}

                disabled={isPending}

            >

                {

                    isPending

                    ?

                    "Generating..."

                    :

                    "Generate Tasks"

                }

            </Button>

            {

                tasks.length>0 &&

                <div className="mt-8 space-y-4">

                    {

                        tasks.map((task,index)=>(

                            <div

                                key={index}

                                className="rounded-2xl bg-[#101018] border border-[#2F2F45] p-5"

                            >

                                <h3 className="font-bold text-lg">

                                    {task.title}

                                </h3>

                                <p className="text-slate-400 mt-2">

                                    {task.description}

                                </p>

                            </div>

                        ))

                    }

                </div>

            }

        </div>

    );

}