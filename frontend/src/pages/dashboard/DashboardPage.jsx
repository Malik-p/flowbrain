import MainLayout from "@/components/layout/MainLayout";
import StatsCards from "@/components/dashboard/StatsCards";
import TaskChart from "@/components/dashboard/TaskChart";
import RecentTasks from "@/components/dashboard/RecentTasks";
import ActivityTimeline from "@/components/dashboard/ActivityTimeline";

import { useDashboard } from "@/hooks/useDashboard";

export default function DashboardPage() {

    const { data, isLoading } = useDashboard();

    if (isLoading) {

        return (

            <MainLayout>

                <div className="text-white text-xl">

                    Loading Dashboard...

                </div>

            </MainLayout>

        );

    }

    const dashboard = data.data;

    return (

        <MainLayout>

            <div className="space-y-8">

                <StatsCards dashboard={dashboard} />

                <div className="grid xl:grid-cols-2 gap-8">

                    <TaskChart dashboard={dashboard} />

                    <RecentTasks />

                </div>

                <ActivityTimeline />

            </div>

        </MainLayout>

    );

}