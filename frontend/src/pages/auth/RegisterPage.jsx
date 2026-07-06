import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { User, Mail, Lock } from "lucide-react";
import { toast } from "sonner";
import { useRegister } from "@/hooks/useAuth";

export default function RegisterPage() {

    const navigate = useNavigate();

    const { mutate, isPending } = useRegister();

    const [form, setForm] = useState({
        name: "",
        email: "",
        password: ""
    });

    const handleRegister = () => {

        mutate(form, {

            onSuccess: (res) => {

                toast.success(res.message);

                navigate("/");

            },

            onError: (err) => {

                toast.error(
                    err.response?.data?.message ||
                    "Registration Failed"
                );

            }

        });

    };

    return (

        <div className="min-h-screen bg-[#09090B] flex justify-center items-center">

            <div className="w-[450px] rounded-3xl bg-[#171722] border border-[#28283d] p-10">

                <h1 className="text-4xl font-bold">

                    Create Account

                </h1>

                <p className="text-slate-400 mt-2">

                    Start managing smarter.

                </p>

                <div className="space-y-5 mt-8">

                    <div className="relative">

                        <User className="absolute left-4 top-3 text-slate-500" />

                        <Input
                            className="pl-12 h-12"
                            placeholder="Full Name"
                            value={form.name}
                            onChange={(e) => setForm({ ...form, name: e.target.value })}
                        />

                    </div>

                    <div className="relative">

                        <Mail className="absolute left-4 top-3 text-slate-500" />

                        <Input
                            className="pl-12 h-12"
                            placeholder="Email"
                            value={form.email}
                            onChange={(e) => setForm({ ...form, email: e.target.value })}
                        />

                    </div>

                    <div className="relative">

                        <Lock className="absolute left-4 top-3 text-slate-500" />

                        <Input
                            type="password"
                            className="pl-12 h-12"
                            placeholder="Password"
                            value={form.password}
                            onChange={(e) => setForm({ ...form, password: e.target.value })}
                        />

                    </div>

                    <Button
                        onClick={handleRegister}
                        disabled={isPending}
                        className="w-full h-12 bg-violet-600 hover:bg-violet-700"
                    >

                        {isPending ? "Creating..." : "Create Account"}

                    </Button>

                    <p className="text-center text-slate-400">

                        Already have an account?

                        <Link
                            className="ml-2 text-violet-400"
                            to="/"
                        >

                            Login

                        </Link>

                    </p>

                </div>

            </div>

        </div>

    );

}