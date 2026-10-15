package dev.ellectronchik.deathtrack.model;

import dev.ellectronchik.deathtrack.Constants;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public final class ItemEntry<T extends Item> {
  private final String name;
  private final ResourceKey<Item> key;
  private final Function<Item.Properties, T> factory;
  private final Consumer<Item.Properties> sharedProperties;

  public ItemEntry(
      String name,
      Function<Item.Properties, T> factory,
      Consumer<Item.Properties> sharedProperties) {
    this.name = name;
    this.key =
        ResourceKey.create(
            Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, name));
    this.factory = factory;
    this.sharedProperties = sharedProperties;
  }

  public T createItem(Item.Properties properties) {
    sharedProperties.accept(properties);

    return factory.apply(properties);
  }

  public T createItem(Consumer<Item.Properties> properties) {
    Item.Properties settings = new Item.Properties();
    sharedProperties.accept(settings);
    properties.accept(settings);

    return factory.apply(settings);
  }

  public String name() {
    return name;
  }

  public ResourceKey<Item> key() {
    return key;
  }
}
