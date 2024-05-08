package org.nguh.nguhcraft.mixin.client;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.nguh.nguhcraft.client.accessors.ClientPlayerListEntryAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin implements ClientPlayerListEntryAccessor {
    @Unique private Text NameAboveHead = null;

    @Override public void setNameAboveHead(Text Name) { NameAboveHead = Name; }
    @Override public Text getNameAboveHead() { return NameAboveHead; }
}