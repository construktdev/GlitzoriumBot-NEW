package de.construkter.glitzoriumID;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Arrays;

public class LoginHandler extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("login")) {
            String email = event.getOption("email").getAsString();
            String password = event.getOption("password").getAsString();
            boolean stayLoggedIn = event.getOption("stayloggedin").getAsBoolean();

            String[] saved = CredentialsHandler.load(email);
            if (saved[0].equals(email) && saved[2].equals(password)) {

                if (stayLoggedIn) {
                    event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(1258052388246523984L)).queue();
                    EmbedBuilder eb = new EmbedBuilder()
                            .setTitle("Logged in")
                            .setDescription("Du wurdest erfolgreich eingeloggt!")
                            .setFooter("GlitzoriumID - BETA")
                            .setColor(Color.GREEN);
                    event.replyEmbeds(eb.build()).setEphemeral(true).queue();
                } else {
                    EmbedBuilder eb = new EmbedBuilder()
                            .setTitle("Logged in")
                            .setDescription("Du wurdest erfolgreich eingeloggt!")
                            .setFooter("GlitzoriumID - BETA")
                            .setColor(Color.GREEN);
                    event.replyEmbeds(eb.build()).setEphemeral(true).queue();
                }
            } else {
                event.reply("Falsche Zugangsdaten").setEphemeral(true).queue();
            }

        }
    }
}

