package de.construkter.commands.slash;

import de.construkter.utils.ID;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SendStatusEmbed extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if  (event.getName().equals("send-status-embed")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)){
                EmbedBuilder eb = new EmbedBuilder();
                eb.setTitle("\uD83D\uDCCA Glitzorium Status \uD83D\uDCCA");
                eb.addField("Discord Musik Bot", "Offline" , false);
                eb.addField("Discord AI Bot", "Online" , false);
                eb.addField("Minecraft Server", "Offline" , false);
                eb.addField("Minecraft Auth Server", "Offline" , false);
                eb.setFooter("Beta");
                TextChannel status = event.getJDA().getTextChannelById(ID.status());
                status.sendMessageEmbeds(eb.build()).queue();
            } else {
                event.getChannel().sendMessage(event.getUser().getAsMention() + " Du hast unzureichende Rechte!").queue();
            }
        }
    }
}
