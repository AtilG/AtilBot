package RPSBot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.voice.VoiceStateUpdateTask;
import io.netty.util.Timeout;
import discord4j.core.event.domain.VoiceStateUpdateEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;

public final class Atilbot {

  public static void main(final String[] args) {
    final String token = "YOUR TOKEN HERE";
    final DiscordClient login = DiscordClient.create(token);
    final GatewayDiscordClient client = login.login().block();

    client.getEventDispatcher().on(ReadyEvent.class).subscribe(event -> {
      final User self = event.getSelf();
      System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));
    });
    options(client);
    client.onDisconnect().block();
  }

  public static void options(GatewayDiscordClient client) {

    client.on(MessageCreateEvent.class).subscribe(event -> {
      final Message message = event.getMessage();
      if ("!AtilRPS".equalsIgnoreCase(message.getContent())) {
        final MessageChannel channel = message.getChannel().block();
        channel.createMessage("Welcome to Atil's Rock, Paper, Scissors game! Win $500 If you beat it! \nPlease enter !rock !paper or !scissors ")
            .block();
          RPSGame(client);
      }
    }); 
  }


  public static void RPSGame(GatewayDiscordClient client) {
    client.on(MessageCreateEvent.class).subscribe(event -> {
      final Message message = event.getMessage();
      if ("!rock".equalsIgnoreCase(message.getContent())) {
        final MessageChannel channel = message.getChannel().block();
        channel.createMessage("Paper you lose!").block();
        return;
      }
      if ("!paper".equalsIgnoreCase(message.getContent())) {
        final MessageChannel channel = message.getChannel().block();
        channel.createMessage("Scissors you lose!").block();
        return;
      }
      if ("!scissors".equalsIgnoreCase(message.getContent())) {
        final MessageChannel channel = message.getChannel().block();
        channel.createMessage("Rock you lose!").block();
        return;
      }
    });
  }
}
