import {
  FolderKanban,
  CheckCircle2,
  Clock3,
  Briefcase,
  ListTodo,
  LoaderCircle,
} from "lucide-react";

export default function StatsCards({ dashboard }) {
  const stats = [
    {
      title: "Workspaces",
      value: dashboard.totalWorkspaces,
      icon: Briefcase,
      color: "from-blue-500 to-cyan-500",
    },
    {
      title: "Projects",
      value: dashboard.totalProjects,
      icon: FolderKanban,
      color: "from-violet-500 to-fuchsia-500",
    },
    {
      title: "Completed",
      value: dashboard.completedTasks,
      icon: CheckCircle2,
      color: "from-green-500 to-emerald-500",
    },
    {
      title: "In Progress",
      value: dashboard.inProgressTasks,
      icon: LoaderCircle,
      color: "from-yellow-500 to-orange-500",
    },
    {
      title: "Todo",
      value: dashboard.todoTasks,
      icon: Clock3,
      color: "from-red-500 to-pink-500",
    },
    {
      title: "Total Tasks",
      value: dashboard.totalTasks,
      icon: ListTodo,
      color: "from-indigo-500 to-violet-500",
    },
  ];

  return (
    <div className="grid xl:grid-cols-3 md:grid-cols-2 gap-6">
      {stats.map((item) => {
        const Icon = item.icon;

        return (
          <div
            key={item.title}
            className="rounded-3xl bg-[#171722] border border-[#262638] p-6 hover:-translate-y-1 transition"
          >
            <div className="flex justify-between">

              <div>

                <p className="text-slate-400">

                  {item.title}

                </p>

                <h2 className="text-4xl font-bold mt-3">

                  {item.value}

                </h2>

              </div>

              <div
                className={`h-16 w-16 rounded-2xl bg-gradient-to-r ${item.color} flex items-center justify-center`}
              >
                <Icon size={28} />
              </div>

            </div>
          </div>
        );
      })}
    </div>
  );
}