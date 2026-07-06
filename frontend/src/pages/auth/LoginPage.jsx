import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { getWorkspaces } from "@/services/workspaceService";

import { Mail, Lock, BrainCircuit } from "lucide-react";

import { toast } from "sonner";

import { useLogin } from "@/hooks/useAuth";

export default function LoginPage() {

    const navigate = useNavigate();

    const { mutate, isPending } = useLogin();

    const [form, setForm] = useState({

        email: "",

        password: ""

    });

    const handleLogin = () => {

    mutate(form, {

        onSuccess: async (res) => {

            console.log("Login Response:", res);

            // Backend response = { success, message, data }

            const loginData = res.data;

            localStorage.setItem(
                "token",
                loginData.token
            );

            localStorage.setItem(
                "user",
                JSON.stringify({
                    name: loginData.name,
                    email: loginData.email,
                    role: loginData.role
                })
            );

            try {

                const workspaceResponse = await getWorkspaces();

                console.log("Workspace Response:", workspaceResponse);

                if (
                    workspaceResponse.data &&
                    workspaceResponse.data.length > 0
                ) {

                    localStorage.setItem(
                        "workspace",
                        JSON.stringify(workspaceResponse.data[0])
                    );

                }

            } catch (e) {

                console.error(e);

            }

            toast.success(res.message);

           navigate("/app");

        },

        onError: (err) => {

            console.error(err);

            toast.error(

                err.response?.data?.message ||

                "Login Failed"

            );

        }

    });

};

    return (

        <div className="min-h-screen bg-[#09090B] flex">

            {/* LEFT */}

            <div className="hidden lg:flex flex-1 relative overflow-hidden">

                <div className="absolute h-[500px] w-[500px] rounded-full bg-violet-700 blur-[180px] opacity-20 top-[-120px] left-[-120px]" />

                <div className="absolute h-[450px] w-[450px] rounded-full bg-pink-500 blur-[180px] opacity-20 bottom-[-100px] right-[-80px]" />

                <div className="relative z-10 flex flex-col justify-center px-24">

                    <BrainCircuit
                        size={70}
                        className="text-violet-500"
                    />

                    <h1 className="text-6xl font-bold mt-8">

                        FlowBrain

                    </h1>

                    <p className="text-slate-400 text-xl mt-6 leading-9">

                        AI Powered Project Management Platform.

                    </p>

                </div>

            </div>

            {/* RIGHT */}

            <div className="flex-1 flex justify-center items-center">

                <div className="w-[430px] rounded-3xl border border-[#28283d] bg-[#171722] p-10">

                    <h2 className="text-4xl font-bold">

                        Welcome Back

                    </h2>

                    <p className="text-slate-400 mt-2">

                        Login to continue

                    </p>

                    <div className="mt-10 space-y-6">

                        <div className="relative">

                            <Mail
                                size={20}
                                className="absolute left-4 top-3 text-slate-500"
                            />

                            <Input

                                placeholder="Email"

                                value={form.email}

                                onChange={(e) =>
                                    setForm({
                                        ...form,
                                        email: e.target.value,
                                    })
                                }

                                className="pl-12 h-12 bg-[#101018]"
                            />

                        </div>

                        <div className="relative">

                            <Lock
                                size={20}
                                className="absolute left-4 top-3 text-slate-500"
                            />

                            <Input

                                type="password"

                                placeholder="Password"

                                value={form.password}

                                onChange={(e) =>
                                    setForm({
                                        ...form,
                                        password: e.target.value,
                                    })
                                }

                                className="pl-12 h-12 bg-[#101018]"
                            />

                        </div>

                        <Button

                            onClick={handleLogin}

                            disabled={isPending}

                            className="w-full h-12 rounded-xl bg-violet-600 hover:bg-violet-700"

                        >

                            {isPending ? "Logging in..." : "Login"}

                        </Button>

                        <p className="text-center text-slate-400">

                            Don't have an account?

                            <Link
                                to="/register"
                                className="ml-2 text-violet-400 hover:text-violet-300"
                            >
                                Register
                            </Link>

                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}