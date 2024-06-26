package co.uk.mommyheather.betonquestgui.network.packet;

import co.uk.mommyheather.betonquestgui.network.PacketHandler;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class PacketAvailablePlayerChoice
{
    private final int id;
    private final String text;

    public PacketAvailablePlayerChoice(int id, String text)
    {
        this.id = id;
        this.text = text;
    }

    public static PacketAvailablePlayerChoice decode(FriendlyByteBuf buffer)
    {
        byte[] data = new byte[buffer.capacity()];
        buffer.getBytes(0, data);
        ByteArrayDataInput input = ByteStreams.newDataInput(data);
        input.readByte();
        return new PacketAvailablePlayerChoice(input.readInt(), input.readUTF());
    }

    public static class Handler
    {
        public static void handle(PacketAvailablePlayerChoice packet, CustomPayloadEvent.Context context)
        {
            context.enqueueWork(() -> PacketHandler.handlePlayerChoice(packet.id, packet.text));
            context.setPacketHandled(true);
        }
    }
}
