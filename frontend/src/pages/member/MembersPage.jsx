import MainLayout from "@/components/layout/MainLayout";
import MembersList from "@/components/member/MembersList";
import InviteMemberDialog from "@/components/member/InviteMemberDialog";

export default function MembersPage() {

    return (

        <MainLayout>

            <div className="space-y-8">

                <InviteMemberDialog />

                <MembersList />

            </div>

        </MainLayout>

    );

}