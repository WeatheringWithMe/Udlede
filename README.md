## Udlede

Export un-internalization text to translation key and retain text.  

## Feature (Now)
- execute /udlede ftbq export to export [FTBQuest](https://www.curseforge.com/minecraft/mc-mods/ftb-quests-forge) un-i18n raw text _(Chapter/RewardTable/Quest, title/subtitle/description)_ and auto-generated translation key (Minecraft Lang Format) to `<gameDir>/exported/ftbquest/en_us.lang`.
- execute /udlede bq export to export [BQ](https://www.curseforge.com/minecraft/mc-mods/better-questing-unofficial) un-i18n raw text _(Chapter/Quest, name/description)_ and auto-generated translation key (Minecraft Lang Format) to `<gameDir>/exported/bq/en_us.lang`.

## Warning!
__Export command export only un-i18n raw text!__  
__It does not export text that is a translation key!__  
__It will override to new export file when you execute export command!__  
__So Manual backup your exported file please!__  

Use ResourceLoader mod to load your Minecraft Lang File.

### TODO
- [x] FTB Quest
- [x] Better Questing Unofficial
- [ ] Patchouli
- [ ] More mod desc export to patchouli desc page file.
- [ ] More mod unlocalization text export!
