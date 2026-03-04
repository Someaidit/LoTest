import { loadConfig } from "./tabs.js";

const LAST_ACTIVE = new Map(); // tabId -> timestamp ms

export function initBurner() {
  chrome.tabs.onActivated.addListener(async ({ tabId }) => {
    const cfg = await loadConfig();
    if (!cfg.features.burnerTabs) return;
    LAST_ACTIVE.set(tabId, Date.now());
  });

  chrome.tabs.onUpdated.addListener(async (tabId, changeInfo) => {
    const cfg = await loadConfig();
    if (!cfg.features.burnerTabs) return;
    if (changeInfo.status === "complete") LAST_ACTIVE.set(tabId, Date.now());
  });

  chrome.alarms.create("tabos_burner_tick", { periodInMinutes: 1 });

  chrome.alarms.onAlarm.addListener(async (alarm) => {
    if (alarm.name !== "tabos_burner_tick") return;

    const cfg = await loadConfig();
    if (!cfg.features.burnerTabs) return;

    const tabs = await chrome.tabs.query({});
    const now = Date.now();
    const maxIdleMs = (cfg.burner.minutesInactiveToClose ?? 15) * 60_000;

    for (const t of tabs) {
      if (!t.id) continue;
      // Do not close pinned or active tabs
      if (t.pinned || t.active) continue;

      const last = LAST_ACTIVE.get(t.id) ?? t.lastAccessed ?? now;
      if (now - last > maxIdleMs) {
        await chrome.tabs.remove(t.id).catch(() => {});
        LAST_ACTIVE.delete(t.id);
      }
    }
  });
}
