package de.construkter.auto.faq;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ButtonHandler extends ListenerAdapter {
    public void onButtonInteraction(ButtonInteractionEvent event) {
        switch (event.getButton().getId()) {
            case "bmehr":
                EmbedBuilder eb = new EmbedBuilder()
                        .setTitle("Join FAQ")
                        .setFooter("Diese nachricht ist automatisiert")
                        .setDescription("Unser letzter Server ist noch nicht so lange her. Um nicht zu riskieren das niemand spielt, und das wir genug Zeit haben einen neuen zu erstellen, warten wir ca. ein halbes Jahr. In der Zeit könnt ihr hier mit anderen Leuten auf anderen Minecraft Servern spielen.")
                        .setColor(Color.CYAN);
                event.deferReply(true).queue();
                event.getHook().sendMessageEmbeds(eb.build()).setEphemeral(true).queue();
                break;
            case "bdel":
                event.deferReply(true).queue();
                event.getMessage().delete().queue();
                event.getHook().sendMessage("Du hast die Nachricht erfolgreich gelöscht").queue();
                break;
        }
    }
}
