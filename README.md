# Disable-Api

Proof of concept mod which extends the Vanilla FeatureFlag system to be used by modders.

Using this system modders can mark their registry objects as requiring a `GameContext` in order for them to be enabled.

This is done in a similar fashion to the Vanilla system, a `Predicate<GameObject>` is required to be set while constructing your registry objects.

See [DisableApiTest](./src/test/java/xyz/apex/forge/disableapi/test/DisableApiTest.java) for an example block, which is only enabled if `jei` is installed.


### Notes
- Disabled elements will still be registered to the game, but will be functionally disabled.
  - Items are given a red `Disabled item` tooltip, to notify the user that the item is disabled.
- Items have 2 additional built in checks with this system.
  - BlockItems are disabled if their respective Block is also disabled.
  - SpawnEggItems are disabled if their respective EntityType is also disabled.
- Mods not adhering to Vanillas FeatureFlag system and not correctly checking if elements are enabled may run into issues.