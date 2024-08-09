package de.construkter.auto.mod;

import de.construkter.utils.ID;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Checker extends ListenerAdapter {
    Member member;
    private static final String[] BAD_WORDS = {
            "arsch", "bitch", "ficken", "shit", "dick", "asshole", "cunt",
            "motherfucker", "bastard", "fuck", "cock", "slut", "whore",
            "douchebag", "piss", "twat", "prick", "wanker", "fag",
            "jerk", "cocksucker", "faggot", "tits", "nigga", "hoe",
            "retard", "skank", "bimbo", "harlot"
    };

    public static boolean containsBadWords(String message) {
        for (String badWord : BAD_WORDS) {
            if (message.toLowerCase().contains(badWord)) {
                return true;
            }
        }
        return false;
    }

    public static String keyword(String message) {
        for (String badWord : BAD_WORDS) {
            if (message.toLowerCase().contains(badWord)) {
                return badWord;
            }
        }
        return "null";
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("AutoMod")
                .setDescription("A bad word got used")
                .setColor(0x66ff99)
                .addField("Nachricht", event.getMessage().getContentRaw(), false)
                .addField("Keyword", keyword(event.getMessage().getContentRaw()) , false)
                .addField("User", event.getAuthor().getName(), true)
                .addField("User ID", event.getAuthor().getId(), true)
                .addField("Guild", event.getGuild().getName(), true)
                .addField("Guild ID", event.getGuild().getId(), true)
                .setFooter("GlitzoriumBot - by Construkter - Beta");
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (containsBadWords(content)) {
            message.delete().queue();
            TextChannel channel = event.getChannel().asTextChannel();
            channel.sendMessage("Bitte achte auf deine Sprache, " + event.getAuthor().getAsMention() + "!").queue();
            member = event.getMember();
            TextChannel c = event.getJDA().getTextChannelById(ID.modlog());
            c.sendMessageEmbeds(eb.build()).addActionRow(
                    Button.danger("bto", "\uD83D\uDCA5 Nutzer Timeout"),
                    Button.danger("bki", "\uD83D\uDCA5 Nutzer kicken"),
                    Button.danger("bbn", "\uD83D\uDCA5 Nutzer bannen")
            ).queue();
        }
    }
    
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        switch (event.getButton().getId()) {
            case "bto":
                try {
                    member.timeoutFor(Duration.ofHours(1)).queue();
                } catch (Exception e){
                    EmbedBuilder eerr = new EmbedBuilder()
                            .setTitle("AutoMod Actions")
                            .setDescription("AutoMod actions failed")
                            .setColor(0x66ff99)
                            .addField("Action", "Timeout user", false)
                            .addField("Error code", "```" + e.getMessage() + "```", false);
                    event.getChannel().sendMessageEmbeds(eerr.build()).queue();
                    break;
                }
                EmbedBuilder ebto  = new EmbedBuilder()
                        .setTitle("AutoMod Actions")
                        .setDescription("AutoMod actions successful")
                        .setColor(0x66ff99)
                        .addField("Action", "Timeout for 1h", false);
                event.getChannel().sendMessageEmbeds(ebto.build()).queue();
                break;
            case "bki":
                try {
                    member.kick().queue();
                } catch (Exception e) {
                    EmbedBuilder eerr = new EmbedBuilder()
                            .setTitle("AutoMod Actions")
                            .setDescription("AutoMod actions failed")
                            .setColor(0x66ff99)
                            .addField("Action", "Kick user", false)
                            .addField("Error code", e.getMessage(), false);
                    event.getChannel().sendMessageEmbeds(eerr.build()).queue();
                    break;
                }

                EmbedBuilder ebki  = new EmbedBuilder()
                        .setTitle("AutoMod Actions")
                        .setDescription("AutoMod actions successful")
                        .setColor(0x66ff99)
                        .addField("Action", "Kicked the User", false);
                event.getChannel().sendMessageEmbeds(ebki.build()).queue();
                break;
            case "bbn":
                try {
                    member.ban(7, TimeUnit.DAYS).queue();
                } catch (Exception e) {
                    EmbedBuilder eerr = new EmbedBuilder()
                            .setTitle("AutoMod Actions")
                            .setDescription("AutoMod actions failed")
                            .setColor(0x66ff99)
                            .addField("Action", "Ban user", false)
                            .addField("Error code", e.getMessage(), false);
                    event.getChannel().sendMessageEmbeds(eerr.build()).queue();
                }
                EmbedBuilder ebbn = new EmbedBuilder()
                        .setTitle("AutoMod Actions")
                        .setDescription("AutoMod actions successful")
                        .setColor(0x66ff99)
                        .addField("Action", "Banned the User", false);
                event.getChannel().sendMessageEmbeds(ebbn.build()).queue();
            case null:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getButton().getId());
        }
    }
}
