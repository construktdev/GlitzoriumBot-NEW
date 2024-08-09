package de.construkter.commands.slash;

import de.construkter.utils.ID;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Message;

public class EditStatusEmbed extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("edit-status-embed")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)){
                String args = event.getOption("args").getAsString();
                boolean online = event.getOption("on-off").getAsBoolean();
                event.getChannel().retrieveMessageById(ID.statusid()).queue(messageToEdit -> {
                    
                });
            }
        }
    }
}
