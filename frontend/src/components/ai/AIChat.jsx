import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { MessageCircle } from "lucide-react";
import { useChat } from "@/hooks/useAI";

export default function AIChat() {

    const [prompt, setPrompt] = useState("");

    const [answer, setAnswer] = useState("");

    const { mutate, isPending } = useChat();

    function send() {

        if (!prompt.trim()) return;

        mutate(

            {

                prompt

            },

            {

                onSuccess: (res) => {

                    setAnswer(

                        res.data.response ||

                        res.data.answer ||

                        res.data.message ||

                        ""

                    );

                }

            }

        );

    }

    return (

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

            <div className="flex items-center gap-3">

                <MessageCircle className="text-violet-400"/>

                <h2 className="text-2xl font-bold">

                    AI Chat

                </h2>

            </div>

            <Input

                className="mt-6"

                placeholder="Ask anything about your workspace..."

                value={prompt}

                onChange={(e)=>setPrompt(e.target.value)}

            />

            <Button

                className="mt-5 bg-violet-600"

                onClick={send}

                disabled={isPending}

            >

                {

                    isPending

                    ?

                    "Thinking..."

                    :

                    "Ask AI"

                }

            </Button>

            {

                answer &&

                <div className="mt-8 rounded-2xl border border-[#2F2F45] bg-[#101018] p-6 whitespace-pre-wrap">

                    {answer}

                </div>

            }

        </div>

    );

}