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

        queryFn:notificationService.getUnreadCount

    });

}

export function useMarkAsRead(){

    const queryClient=useQueryClient();

    return useMutation({

        mutationFn:notificationService.markAsRead,

        onSuccess:()=>{

            queryClient.invalidateQueries({

                queryKey:["notifications"]

            });

            queryClient.invalidateQueries({

                queryKey:["notification-count"]

            });

        }

    });

}