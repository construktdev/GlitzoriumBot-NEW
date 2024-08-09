package de.construkter.auto.faq;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class JoinFAQ extends ListenerAdapter {
    String[] jointrigs =  {"join", "beitreten", "spielen"};
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        for (String jointrig : jointrigs) {
            if (message.toLowerCase().contains(jointrig)) {
                EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Join FAQ")
                        .setDescription("Zurzeit haben wir noch keinen Server der öffentlich Zugänglich ist. Wir werden aber sobald wir nähere Infos haben es ankündigen!")
                        .setFooter("Diese nachricht ist automatisiert")
                        .setColor(Color.CYAN);
                event.getChannel().sendMessageEmbeds(eb.build())
                        .setMessageReference(event.getMessage())
                        .addActionRow(
                                Button.primary("bmehr", "Mehr erfahren"),
                                Button.danger("bdel", "Löschen")
                        )
                        .queue();
            }
        }
    }
}
