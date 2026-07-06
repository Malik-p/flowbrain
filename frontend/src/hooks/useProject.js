import {
    useQuery,
    useMutation,
    useQueryClient
} from "@tanstack/react-query";

import * as projectService from "@/services/projectService";

export function useProjects() {

    return useQuery({

        queryKey: ["projects"],

        queryFn: projectService.getProjects

    });

}

export function useCreateProject() {

    const queryClient = useQueryClient();

    return useMutation({

        mutationFn: projectService.createProject,

        onSuccess: () => {

            queryClient.invalidateQueries({

                queryKey: ["projects"]

            });

        }

    });

}

export function useDeleteProject() {

    const queryClient = useQueryClient();

    return useMutation({

        mutationFn: projectService.deleteProject,

        onSuccess: () => {

            queryClient.invalidateQueries({

                queryKey: ["projects"]

            });

        }

    });

}