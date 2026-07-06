import Sidebar from "./Sidebar";
import Navbar from "./Navbar";

export default function MainLayout({ children }) {

  return (

    <div className="min-h-screen bg-[#09090B] text-white flex overflow-hidden">

      <div className="fixed left-[-200px] top-[-200px] h-[500px] w-[500px] rounded-full bg-violet-700 opacity-20 blur-[180px]" />

      <div className="fixed bottom-[-250px] right-[-150px] h-[450px] w-[450px] rounded-full bg-pink-600 opacity-20 blur-[180px]" />

      <Sidebar />

      <div className="flex-1 flex flex-col">

        <Navbar />

        <main className="flex-1 overflow-auto p-8">

          {children}

        </main>

      </div>

    </div>

  )

}