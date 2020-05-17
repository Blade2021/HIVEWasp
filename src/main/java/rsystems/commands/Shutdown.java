package rsystems.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rsystems.HiveBot;

public class Shutdown extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        if(event.getAuthor().isBot()){
            return;
        }

        String message = event.getMessage().getContentRaw();

        if ((message.equalsIgnoreCase(HiveBot.prefix + "shutdown")) || (message.equalsIgnoreCase(HiveBot.prefix + "sd"))) {
            try {
                if (event.getMessage().getMember().hasPermission(Permission.ADMINISTRATOR)) {
                    event.getChannel().sendMessage("Shutting down...").queue();
                    System.out.println("Shut down called by " + event.getMessage().getAuthor().getName());
                    event.getJDA().shutdown();
                    //event.getJDA().shutdownNow();
                } else {
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " You do not have access to that command").queue();
                }
            } catch (NullPointerException e) {
                System.out.println("Null permission found");
            }
        }
    }
}