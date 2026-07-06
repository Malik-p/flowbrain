import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getWorkspaces } from "@/services/workspaceService";

export default function AppInitializer() {

    const navigate = useNavigate();

    useEffect(() => {

        const token = localStorage.getItem("token");

        if (!token) {

            navigate("/");

            return;

        }

        async function initialize() {

            try {

                const response = await getWorkspaces();

                const workspaces = response.data;

                if (!workspaces || workspaces.length === 0) {

                    navigate("/create-workspace");

                    return;

                }

                localStorage.setItem(

                    "workspace",

                    JSON.stringify(workspaces[0])

                );

                navigate("/dashboard");

            } catch (err) {

                console.log(err);

                navigate("/");

            }

        }

        initialize();

    }, []);

    return null;

}