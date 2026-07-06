import {

useQuery,

useMutation,

useQueryClient

}

from "@tanstack/react-query";

import * as taskService from "@/services/taskService";

export function useTasks(){

    return useQuery({

        queryKey:["tasks"],

        queryFn:taskService.getTasks

    });

}

export function useCreateTask(){

    const qc=useQueryClient();

    return useMutation({

        mutationFn:taskService.createTask,

        onSuccess:()=>{

            qc.invalidateQueries({

                queryKey:["tasks"]

            });

        }

    });

}

export function useDeleteTask(){

    const qc=useQueryClient();

    return useMutation({

        mutationFn:taskService.deleteTask,

        onSuccess:()=>{

            qc.invalidateQueries({

                queryKey:["tasks"]

            });

        }

    });

}