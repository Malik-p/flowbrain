import { useState } from "react";

import { Button } from "@/components/ui/button";

import { Input } from "@/components/ui/input";

import { toast } from "sonner";

import { useInviteMember } from "@/hooks/useWorkspace";

export default function InviteMemberDialog() {

    const { mutate, isPending } = useInviteMember();

    const [email, setEmail] = useState("");

    function invite() {

        if (!email) {

            toast.error("Enter email");

            return;

        }

        mutate(

            {

                email

            },

            {

                onSuccess: (res) => {

                    toast.success(res.message);

                    setEmail("");

                },

                onError: (err) => {

                    toast.error(

                        err.response?.data?.message ||

                        "Invitation failed"

                    );

                }

            }

        );

    }

    return (

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

            <h2 className="text-2xl font-bold">

                Invite Member

            </h2>

            <div className="flex gap-4 mt-6">

                <Input

                    placeholder="member@gmail.com"

                    value={email}

                    onChange={(e) => setEmail(e.target.value)}

                />

                <Button

                    onClick={invite}

                    disabled={isPending}

                    className="bg-violet-600"

                >

                    Invite

                </Button>

            </div>

        </div>

    );

}