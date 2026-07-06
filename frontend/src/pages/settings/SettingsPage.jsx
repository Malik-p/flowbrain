import MainLayout from "@/components/layout/MainLayout";

export default function SettingsPage() {
    return (
        <MainLayout>
            <div className="space-y-6">

                <h1 className="text-4xl font-bold">
                    Settings
                </h1>

                <div className="rounded-3xl bg-[#171722] border border-[#262638] p-8">

                    <h2 className="text-2xl font-semibold">
                        Settings
                    </h2>

                    <p className="text-slate-400 mt-4">
                        Profile settings, workspace settings and preferences will be added here.
                    </p>

                </div>

            </div>
        </MainLayout>
    );
}