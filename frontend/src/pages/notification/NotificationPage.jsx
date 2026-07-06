import MainLayout from "@/components/layout/MainLayout";
import NotificationCard from "@/components/notification/NotificationCard";
import { useNotifications } from "@/hooks/useNotification";

export default function NotificationPage(){

    const {data,isLoading}=useNotifications();

    if(isLoading){

        return(

            <MainLayout>

                Loading...

            </MainLayout>

        )

    }

    return(

        <MainLayout>

            <h1 className="text-4xl font-bold">

                Notifications

            </h1>

            <div className="space-y-5 mt-8">

                {data?.data?.map(notification=>(

                    <NotificationCard

                        key={notification.id}

                        notification={notification}

                    />

                ))}

            </div>

        </MainLayout>

    )

}