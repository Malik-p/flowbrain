import { Search, Bell } from "lucide-react";
import { motion } from "framer-motion";
import UserMenu from "./UserMenu";
import { useUnreadCount } from "@/hooks/useNotification";

export default function Navbar() {

  const user = JSON.parse(localStorage.getItem("user") || "{}");

  const { data } = useUnreadCount();

  const unread = data?.data || 0;

  return (

    <motion.header
      initial={{ y: -40, opacity: 0 }}
      animate={{ y: 0, opacity: 1 }}
      transition={{ duration: 0.4 }}
      className="sticky top-0 z-50 h-24 px-10 flex items-center justify-between bg-[#0B0B12]/80 backdrop-blur-xl border-b border-[#242437]"
    >

      <div>

        <h2 className="text-3xl font-bold">

          Welcome Back 👋

        </h2>

        <p className="text-slate-400 mt-1">

          {

            user?.name

              ?

              `Welcome, ${user.name}`

              :

              "AI Powered Workspace"

          }

        </p>

      </div>

      <div className="flex items-center gap-5">

        <div className="bg-[#171722] border border-[#2A2A3D] rounded-2xl px-5 h-12 flex items-center gap-3">

          <Search size={18} />

          <input
            placeholder="Search anything..."
            className="bg-transparent outline-none placeholder:text-slate-500 w-56"
          />

        </div>

        <button className="relative h-12 w-12 rounded-2xl bg-[#171722] hover:bg-[#202031] transition flex items-center justify-center">

          <Bell size={20} />

          {

            unread > 0 &&

            <span className="absolute -top-1 -right-1 h-6 w-6 rounded-full bg-red-500 text-xs flex items-center justify-center text-white">

              {unread}

            </span>

          }

        </button>

        <div className="flex items-center gap-4">

          <div className="h-12 w-12 rounded-full bg-gradient-to-r from-violet-500 to-fuchsia-500 flex items-center justify-center font-bold text-white text-lg">

            {

              user?.name

                ?

                user.name.charAt(0).toUpperCase()

                :

                "U"

            }

          </div>

          <UserMenu />

        </div>

      </div>

    </motion.header>

  );

}