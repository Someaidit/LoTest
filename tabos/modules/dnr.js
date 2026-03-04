import { loadConfig } from "./tabs.js";

export function initDNR() {
  // Keep ruleset enabled/disabled via config
  chrome.storage.onChanged.addListener(async (changes, area) => {
    if (area !== "sync") return;
    if (!changes?.tabos_config) return;

    const cfg = changes.tabos_config.newValue;
    const enable = !!cfg.features.dnrTrackers;

    // Turn the packaged ruleset on/off
    await chrome.declarativeNetRequest.updateEnabledRulesets({
      enableRulesetIds: enable ? ["trackers"] : [],
      disableRulesetIds: enable ? [] : ["trackers"]
    }).catch(() => {});
  });
}
