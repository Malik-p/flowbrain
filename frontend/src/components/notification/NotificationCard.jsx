import { Button } from "@/components/ui/button";
import { useMarkAsRead } from "@/hooks/useNotification";

export default function NotificationCard({ notification }) {

    const { mutate } = useMarkAsRead();

    function markRead() {

        mutate(notification.id);

    }

    return (

        <div
            className={`rounded-2xl border p-6 ${
                notification.read
                    ? "border-[#262638] bg-[#171722]"
                    : "border-violet-600 bg-[#1C1C2B]"
            }`}
        >

            <div className="flex justify-between items-start">

                <div>

                    <h2 className="text-xl font-bold">

                        {notification.title}

                    </h2>

                    <p className="text-slate-400 mt-2">

                        {notification.message}

                    </p>

                    <p className="text-xs text-slate-500 mt-4">

                        {new Date(notification.createdAt).toLocaleString()}

                    </p>

                </div>

                {

                    !notification.read &&

                    <Button

                        onClick={markRead}

                        className="bg-violet-600"

                    >

                        Mark Read

                    </Button>

                }

            </div>

        </div>

    );

}