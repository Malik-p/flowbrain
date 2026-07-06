import MainLayout from "@/components/layout/MainLayout";
import NotificationCard from "@/components/notification/NotificationCard";
import { useNotifications } from "@/hooks/useNotification";

export default function NotificationPage() {

    const { data, isLoading } = useNotifications();

    if (isLoading) {

        return (

            <MainLayout>

                <div className="text-xl">

                    Loading Notifications...

                </div>

            </MainLayout>

        );

    }

    const notifications = data?.data || [];

    return (

        <MainLayout>

            <h1 className="text-4xl font-bold">

                Notifications

            </h1>

            <div className="space-y-5 mt-8">

                {

                    notifications.length === 0 ?

                        (

                            <div className="rounded-3xl border border-dashed border-[#33334A] p-12 text-center text-slate-400">

                                No Notifications Yet

                            </div>

                        )

                        :

                        (

                            notifications.map(notification => (

                                <NotificationCard

                                    key={notification.id}

                                    notification={notification}

                                />

                            ))

                        )

                }

            </div>

        </MainLayout>

    );

}