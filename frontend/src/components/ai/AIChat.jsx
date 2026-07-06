import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { useChat } from "@/hooks/useAI";

export default function AIChat() {

  const workspace = JSON.parse(localStorage.getItem("workspace"));

  const [prompt, setPrompt] = useState("");
  const [answer, setAnswer] = useState("");

  const { mutate, isPending } = useChat();

  function send() {

    mutate(
      {
        workspaceId: workspace.id,
        prompt,
      },
      {
        onSuccess: (res) => {
          setAnswer(res.data.answer);
        },
      }
    );
  }

  return (
    <div className="rounded-3xl bg-[#171722] border border-[#262638] p-6">

      <h2 className="text-2xl font-bold mb-6">

        AI Chat

      </h2>

      <Input
        value={prompt}
        onChange={(e) => setPrompt(e.target.value)}
        placeholder="Ask FlowBrain AI..."
      />

      <Button
        className="mt-4"
        onClick={send}
        disabled={isPending}
      >
        {isPending ? "Thinking..." : "Send"}
      </Button>

      {answer && (
        <div className="mt-8 rounded-xl bg-[#0F0F16] p-5 border border-[#2A2A3D] whitespace-pre-wrap">
          {answer}
        </div>
      )}

    </div>
  );
}