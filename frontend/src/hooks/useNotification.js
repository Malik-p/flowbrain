import {

    useQuery,

    useMutation,

    useQueryClient

} from "@tanstack/react-query";

import * as notificationService from "@/services/notificationService";

export function useNotifications() {

    return useQuery({

        queryKey:["notifications"],

        queryFn:notificationService.getNotifications

    });

}

export function useUnreadCount(){

    return useQuery({

        queryKey:["notification-count"],

        queryFn:notificationService.getUnreadCount,

        refetchInterval:5000

    });

}

export function useMarkAsRead(){

    const qc=useQueryClient();

    return useMutation({

        mutationFn:notificationService.markAsRead,

        onSuccess:()=>{

            qc.invalidateQueries({

                queryKey:["notifications"]

            });

            qc.invalidateQueries({

                queryKey:["notification-count"]

            });

        }

    });

}