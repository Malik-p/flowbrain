import { Button } from "@/components/ui/button";
import { useMarkAsRead } from "@/hooks/useNotification";
import { toast } from "sonner";

export default function NotificationCard({notification}){

    const {mutate}=useMarkAsRead();

    function markRead(){

        mutate(notification.id,{

            onSuccess:(res)=>{

                toast.success(res.message);

            }

        });

    }

    return(

        <div className="rounded-2xl bg-[#171722] border border-[#262638] p-5">

            <h2 className="font-semibold">

                {notification.title}

            </h2>

            <p className="text-slate-400 mt-2">

                {notification.message}

            </p>

            {!notification.read && (

                <Button

                    className="mt-5"

                    onClick={markRead}

                >

                    Mark as Read

                </Button>

            )}

        </div>

    )

}