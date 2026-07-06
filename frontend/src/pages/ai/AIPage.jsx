import MainLayout from "@/components/layout/MainLayout";
import AIChat from "@/components/ai/AIChat";
import AIActions from "@/components/ai/AIActions";
import GenerateTask from "@/components/ai/GenerateTask";

export default function AIPage() {
  return (
    <MainLayout>
      <div className="grid xl:grid-cols-3 gap-8">

        <div className="space-y-8">

    <GenerateTask/>

    <AIChat/>

</div>

        <AIActions />

      </div>
    </MainLayout>
  );
}