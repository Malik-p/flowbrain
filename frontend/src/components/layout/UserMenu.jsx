import { LogOut } from "lucide-react";
import { useNavigate } from "react-router-dom";

export default function UserMenu() {

    const navigate = useNavigate();

    const logout = () => {

        localStorage.clear();

        navigate("/");

    };

    return (

        <button

            onClick={logout}

            className="rounded-xl bg-red-500/10 px-4 py-2 text-red-400 hover:bg-red-500/20 flex items-center gap-2"

        >

            <LogOut size={18} />

            Logout

        </button>

    );

}