import {
    useQuery,
    useMutation,
    useQueryClient
} from "@tanstack/react-query";

import {
    getMembers,
    inviteMemberByEmail
} from "@/services/workspaceService";

export function useMembers() {

    return useQuery({

        queryKey: ["members"],

        queryFn: getMembers

    });

}

export function useInviteMember() {

    const qc = useQueryClient();

    return useMutation({

        mutationFn: inviteMemberByEmail,

        onSuccess: () => {

            qc.invalidateQueries({

                queryKey: ["members"]

            });

        }

    });

}