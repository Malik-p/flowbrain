import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";

import LoginPage from "@/pages/auth/LoginPage";
import RegisterPage from "@/pages/auth/RegisterPage";
import DashboardPage from "@/pages/dashboard/DashboardPage";
import ProjectsPage from "@/pages/project/ProjectsPage";
import TasksPage from "@/pages/task/TasksPage";
import NotificationPage from "@/pages/notification/NotificationPage";
import AIPage from "@/pages/ai/AIPage";
import SettingsPage from "@/pages/settings/SettingsPage";
import AppInitializer from "@/routes/AppInitializer";
import CreateWorkspacePage from "@/pages/onboarding/CreateWorkspacePage";
import ProtectedRoute from "@/routes/ProtectedRoute";
import MembersPage from "@/pages/member/MembersPage";

export default function AppRoutes() {

  return (

    <BrowserRouter>

      <Routes>

        <Route path="/" element={<LoginPage />} />

        <Route path="/register" element={<RegisterPage />} />

        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <DashboardPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/projects"
          element={
            <ProtectedRoute>
              <ProjectsPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/tasks"
          element={
            <ProtectedRoute>
              <TasksPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/notifications"
          element={
            <ProtectedRoute>
              <NotificationPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/ai"
          element={
            <ProtectedRoute>
              <AIPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/settings"
          element={
            <ProtectedRoute>
              <SettingsPage />
            </ProtectedRoute>
          }
        />

        <Route

          path="/app"

          element={

            <ProtectedRoute>

              <AppInitializer />

            </ProtectedRoute>

          }

        />

        <Route

          path="/create-workspace"

          element={

            <ProtectedRoute>

              <CreateWorkspacePage />

            </ProtectedRoute>

          }

        />

        <Route
          path="/members"
          element={
            <ProtectedRoute>
              <MembersPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/create-workspace"
          element={
            <ProtectedRoute>
              <CreateWorkspacePage />
            </ProtectedRoute>
          }
        />

      </Routes>

    </BrowserRouter>

  );

}