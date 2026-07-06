import { useQuery } from "@tanstack/react-query";
import { getMembers } from "@/services/memberService";

export function useMembers() {

    return useQuery({

        queryKey: ["members"],

        queryFn: getMembers

    });

}