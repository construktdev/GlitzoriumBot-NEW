package de.construkter.commands.slash;

import de.construkter.utils.ID;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class HelpCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "help":
                EmbedBuilder help = new EmbedBuilder()
                        .setTitle("Command Liste")
                        .setDescription("Unten findest du eine Liste mit allen verfügbaren Commands und eine erklärung was diese machen!")
                        .addField("/help", "Zeigt dir diesen Embed", false)
                        .setColor(0x66ff99);
                //Erweiterungen
                event.replyEmbeds(help.build()).queue();
                break;
        }
        EmbedBuilder log = new EmbedBuilder()
                .setTitle("Command Logger")
                .setDescription("A command got executed")
                .setColor(0x66ff99)
                .addField("Command", "/" + event.getName(), false)
                .addField("User", event.getUser().getName(), true)
                .addField("User ID", event.getUser().getId(), true)
                .addField("Guild", event.getGuild().getName(), true)
                .addField("Guild ID", event.getGuild().getId(), true)
                .setFooter("GlitzoriumBot - by Construkter - Beta");
        TextChannel modlog = event.getJDA().getTextChannelById(ID.modlog());
        modlog.sendMessageEmbeds(log.build()).queue();
    }
}
