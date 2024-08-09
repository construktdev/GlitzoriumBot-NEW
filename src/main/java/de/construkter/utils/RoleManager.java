package de.construkter.utils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class RoleManager {
    private JDA jda;

    public RoleManager(JDA jda) {
        this.jda = jda;
    }

    public void grantRoleByID(String guildID, String memberID, String roleID) {
        Guild guild = jda.getGuildById(guildID);
        if (guild == null) {
            System.out.println("Guild not found");
            return;
        }

        Role role = guild.getRoleById(roleID);
        if (role == null) {
            System.out.println("Role not found");
            return;
        }

        Member member = guild.getMemberById(memberID);
        if (member == null) {
            System.out.println("Member not found");
            return;
        }

        guild.addRoleToMember(member, role).queue();
    }
}
