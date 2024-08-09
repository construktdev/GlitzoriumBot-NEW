package de.construkter.listeners;

import de.construkter.utils.ID;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnReady extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Bot is ready");
        eb.setDescription("Diese Instanz des Glitzorium Bots ist nun bereit!");
        eb.addField("Guilds: ", String.valueOf(event.getJDA().getGuilds().size()), false);
        eb.addField("Status:", "", false);
        eb.addField("Slash-Commands:", "Bereit", true);
        eb.addField("Text-Commands:", "Bereit", true);
        eb.addField("Automatisierungen:", "Bereit", true);
        eb.setFooter("GlitzoriumBot - by Construkter - Beta");
        TextChannel c = event.getJDA().getTextChannelById(ID.console());
        c.sendMessageEmbeds(eb.build()).queue();
        System.out.println(event.getJDA().getInviteUrl());
    }
}
