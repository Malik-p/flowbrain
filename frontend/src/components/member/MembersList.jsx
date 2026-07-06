import { useMembers } from "@/hooks/useWorkspace";

export default function MembersList() {

    const { data, isLoading } = useMembers();

    if (isLoading) {

        return <div>Loading Members...</div>;

    }

    const members = data?.data || [];

    return (

        <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

            <h2 className="text-2xl font-bold">

                Workspace Members

            </h2>

            <div className="mt-8 space-y-4">

                {

                    members.map(member => (

                        <div

                            key={member.id}

                            className="flex justify-between items-center border border-[#2F2F45] rounded-xl p-4"

                        >

                            <div>

                                <p className="font-semibold">

                                    {member.name}

                                </p>

                                <p className="text-slate-400">

                                    {member.email}

                                </p>

                            </div>

                            <span className="text-violet-400">

                                {member.role}

                            </span>

                        </div>

                    ))

                }

            </div>

        </div>

    );

}