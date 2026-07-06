import { useState } from "react";

import { Button } from "@/components/ui/button";

import {
  useSprintSummary,
  useProjectHealth,
  useStandup,
  useSuggestions,
} from "@/hooks/useAI";

export default function AIActions() {

  const workspace = JSON.parse(
    localStorage.getItem("workspace")
  );

  const workspaceId = workspace?.id;

  const sprint = useSprintSummary();
  const health = useProjectHealth();
  const standup = useStandup();
  const suggestion = useSuggestions();

  const [result, setResult] = useState("");

  return (

    <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

      <h2 className="text-2xl font-bold">

        AI Workspace Assistant

      </h2>

      <div className="grid grid-cols-2 gap-4 mt-8">

        <Button

          className="bg-violet-600"

          onClick={() =>

            sprint.mutate(

              workspaceId,

              {

                onSuccess: (res) =>

                  setResult(

                    res.data.summary ||

                    res.data.response ||

                    ""

                  )

              }

            )

          }

        >

          Sprint Summary

        </Button>

        <Button

          className="bg-violet-600"

          onClick={() =>

            health.mutate(

              workspaceId,

              {

                onSuccess: (res) =>

                  setResult(

                    res.data.health ||

                    res.data.response ||

                    ""

                  )

              }

            )

          }

        >

          Project Health

        </Button>

        <Button

          className="bg-violet-600"

          onClick={() =>

            standup.mutate(

              workspaceId,

              {

                onSuccess: (res) =>

                  setResult(

                    res.data.standup ||

                    res.data.response ||

                    ""

                  )

              }

            )

          }

        >

          Daily Standup

        </Button>

        <Button

          className="bg-violet-600"

          onClick={() =>

            suggestion.mutate(

              workspaceId,

              {

                onSuccess: (res) =>

                  setResult(

                    res.data.suggestions ||

                    res.data.response ||

                    ""

                  )

              }

            )

          }

        >

          Suggestions

        </Button>

      </div>

      {

        result &&

        <div className="mt-8 rounded-2xl border border-[#2F2F45] bg-[#101018] p-6 whitespace-pre-wrap">

          {result}

        </div>

      }

    </div>

  );

}