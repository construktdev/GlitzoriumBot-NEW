package de.construkter.glitzoriumID;

import de.construkter.glitzoriumID.minecraft.CodeManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.awt.*;

public class RegisterHandler extends ListenerAdapter {
    public static String[] getCode() {
        return code;
    }

    static String[] code = new String[3];
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("register")) {
            Modal modal = Modal.create("register", "GlitzoriumID - Registrieren")
                    .addActionRows(
                            ActionRow.of(TextInput.create("email", "Email-Adresse", TextInputStyle.SHORT).setRequired(false).build()),
                            ActionRow.of(TextInput.create("uname", "Minecraft Name", TextInputStyle.SHORT).setRequired(true).build()),
                            ActionRow.of(TextInput.create("pass", "Passwort", TextInputStyle.SHORT).setRequired(true).build()),
                            ActionRow.of(TextInput.create("passcheck", "Passwort wiederholen", TextInputStyle.SHORT).setRequired(true).build())
                    ).build();
            event.replyModal(modal).queue();
        }
    }

    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getInteraction().getModalId().equals("register")){
            String email = event.getValue("email").getAsString();
            String username = event.getValue("uname").getAsString();
            String password = event.getValue("pass").getAsString();
            String passcheck = event.getValue("passcheck").getAsString();
            if (!password.equals(passcheck)) {
                EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Warnung")
                        .setDescription("Deine Passwörter stimmen nicht überein!")
                        .setColor(Color.RED);
                event.replyEmbeds(eb.build()).queue();
            } else {
                if (CredentialsHandler.save(email, username, password)) {
                    int[] auth = CodeManager.gen();
                    event.reply("**" + auth[0] + "** ist dein Auth Code. Gebe diesen in Minecraft (**auth.glitzorium.de**) mit **/auth [code]** ein! ");
                    code[0] = String.valueOf(auth[0]);
                    code[1] = String.valueOf(auth[1]);
                    code[2] = username;

                        EmbedBuilder eb = new EmbedBuilder()
                                .setTitle("Erfolgreich!")
                                .setDescription("Du hast dich erfolgreich verifiziert und kannst dich nun anmelden!")
                                .setColor(Color.GREEN);
                        event.replyEmbeds(eb.build()).setEphemeral(true).queue();

                } else {
                    EmbedBuilder eb = new EmbedBuilder()
                            .setTitle("Fehler")
                            .setFooter("GlitzoriumID - BETA")
                            .setDescription("Entweder ist deine Email/Minecraft Name bereits registriert, oder es gab einen internen Fehler!")
                            .setColor(Color.RED);
                    event.replyEmbeds(eb.build()).setEphemeral(true).queue();
                }
            }
        }

    }
}
