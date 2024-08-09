package de.construkter.commands.slash;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.awt.*;

public class EmbedDesigner extends ListenerAdapter {
    @Override
   public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
       if (event.getName().equals("embed-builder")) {
           if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)){
               event.reply("Du hast unzureichende Rechte um diesen Befehl zu nutzen.").setEphemeral(true).queue();
               return;
           }
           Modal modal = Modal.create("embed-builder", "Embed Builder")
                   .addActionRows(
                           ActionRow.of(TextInput.create("title", "Titel", TextInputStyle.SHORT).setRequired(true).build()),
                           ActionRow.of(TextInput.create("description", "Beschriebung", TextInputStyle.PARAGRAPH).setRequired(true).build()),
                           ActionRow.of(TextInput.create("footer", "Fußzeile", TextInputStyle.SHORT).setRequired(true).build()),
                           ActionRow.of(TextInput.create("channel", "Kanal ID", TextInputStyle.SHORT).setRequired(true).build())
                   ).build();
           event.replyModal(modal).queue();
       }
   }
    @Override
  public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getInteraction().equals("embed-builder")) {
            String title = event.getValue("title").getAsString();
            String description = event.getValue("description").getAsString();
            String footer = event.getValue("footer").getAsString();
            long cid = Long.parseLong(event.getValue("channel").getAsString());
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle(title)
                    .setDescription(description)
                    .setFooter(footer)
                    .setColor(Color.CYAN);
            event.getJDA().getTextChannelById(cid).sendMessageEmbeds(embed.build()).queue();
            event.reply("Sending the built Embed in <#" + cid + "> " + event.getUser().getAsMention()).queue();
        }

  }
}
