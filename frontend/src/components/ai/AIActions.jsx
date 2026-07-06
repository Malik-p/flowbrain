import { Button } from "@/components/ui/button";
import { toast } from "sonner";

import {
  useSprintSummary,
  useProjectHealth,
  useStandup,
  useSuggestions,
} from "@/hooks/useAI";

export default function AIActions() {

  const workspace = JSON.parse(localStorage.getItem("workspace"));

  const sprint = useSprintSummary();
  const health = useProjectHealth();
  const standup = useStandup();
  const suggestion = useSuggestions();

  const workspaceId = workspace?.id;

  return (
    <div className="rounded-3xl bg-[#171722] border border-[#262638] p-6 space-y-4">

      <h2 className="text-2xl font-bold">

        AI Workspace

      </h2>

      <Button
        className="w-full"
        onClick={() =>
          sprint.mutate(workspaceId, {
            onSuccess: (res) => toast.success(res.message),
          })
        }
      >
        Sprint Summary
      </Button>

      <Button
        className="w-full"
        onClick={() =>
          health.mutate(workspaceId, {
            onSuccess: (res) => toast.success(res.message),
          })
        }
      >
        Project Health
      </Button>

      <Button
        className="w-full"
        onClick={() =>
          standup.mutate(workspaceId, {
            onSuccess: (res) => toast.success(res.message),
          })
        }
      >
        Daily Standup
      </Button>

      <Button
        className="w-full"
        onClick={() =>
          suggestion.mutate(workspaceId, {
            onSuccess: (res) => toast.success(res.message),
          })
        }
      >
        AI Suggestions
      </Button>

    </div>
  );
}