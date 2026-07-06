import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useGenerateTask } from "@/hooks/useAI";

export default function GenerateTask() {

    const workspace = JSON.parse(
        localStorage.getItem("workspace")
    );

    const [prompt,setPrompt]=useState("");

    const {mutate,isPending,data}=useGenerateTask();

    function generate(){

        mutate({

            workspaceId:workspace.id,

            prompt

        });

    }

    return(

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-6">

            <h2 className="text-2xl font-bold">

                AI Task Generator

            </h2>

            <Input

                className="mt-6"

                placeholder="Example : Build Authentication Module"

                value={prompt}

                onChange={(e)=>setPrompt(e.target.value)}

            />

            <Button

                className="mt-5"

                onClick={generate}

                disabled={isPending}

            >

                {isPending

                ?"Generating..."

                :"Generate"}

            </Button>

            <div className="mt-8 space-y-4">

                {data?.data?.tasks?.map((task,index)=>(

                    <div

                        key={index}

                        className="rounded-xl bg-[#101018] p-5 border border-[#303040]"

                    >

                        <h3 className="font-bold">

                            {task.title}

                        </h3>

                        <p className="text-slate-400 mt-2">

                            {task.description}

                        </p>

                    </div>

                ))}

            </div>

        </div>

    );

}