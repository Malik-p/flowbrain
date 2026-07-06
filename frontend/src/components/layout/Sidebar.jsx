import {
  LayoutDashboard,
  FolderKanban,
  CheckSquare,
  Bell,
  Bot,
  Settings,
  LogOut
} from "lucide-react";
import { NavLink } from "react-router-dom";
import { motion } from "framer-motion";
import { Users } from "lucide-react";

const menus = [
  {
    title: "Dashboard",
    path: "/dashboard",
    icon: LayoutDashboard,
  },
  {
    title: "Projects",
    path: "/projects",
    icon: FolderKanban,
  },
  {
    title: "Members",
    path: "/members",
    icon: Users,
  },
  {
    title: "Tasks",
    path: "/tasks",
    icon: CheckSquare,
  },
  {
    title: "Notifications",
    path: "/notifications",
    icon: Bell,
  },
  {
    title: "AI Assistant",
    path: "/ai",
    icon: Bot,
  },
  {
    title: "Settings",
    path: "/settings",
    icon: Settings,
  },
];

export default function Sidebar() {

  return (

    <motion.aside
      initial={{ x: -80, opacity: 0 }}
      animate={{ x: 0, opacity: 1 }}
      transition={{ duration: .5 }}
      className="w-72 bg-[#111118]/90 backdrop-blur-xl border-r border-[#252535] flex flex-col"
    >

      <div className="h-24 flex items-center px-8">

        <h1 className="text-3xl font-extrabold bg-gradient-to-r from-violet-400 via-fuchsia-500 to-pink-500 bg-clip-text text-transparent">

          FlowBrain

        </h1>

      </div>

      <nav className="flex-1 px-4 space-y-2">

        {menus.map((item) => {

          const Icon = item.icon;

          return (

            <NavLink
              key={item.title}
              to={item.path}
              className={({ isActive }) =>

                `flex items-center gap-4 px-5 py-4 rounded-2xl transition-all duration-300

                ${isActive

                  ? 'bg-gradient-to-r from-violet-600 to-fuchsia-600 text-white shadow-lg'

                  : 'text-slate-400 hover:bg-[#1B1B28] hover:text-white'

                }`

              }
            >

              <Icon size={21} />

              <span>{item.title}</span>

            </NavLink>

          )

        })}

      </nav>

      <div className="p-5">

        <button className="w-full rounded-2xl py-4 flex items-center justify-center gap-3 bg-red-500/10 hover:bg-red-500/20 text-red-400">

          <LogOut size={20} />

          Logout

        </button>

      </div>

    </motion.aside>

  );

}