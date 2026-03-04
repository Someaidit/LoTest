import { loadConfig, saveConfig } from "./modules/tabs.js";
import { initTabFeatures, registerContextMenus } from "./modules/tabs.js";
import { initBurner } from "./modules/burner.js";
import { initSnapshot } from "./modules/snapshot.js";
import { initDNR } from "./modules/dnr.js";

chrome.runtime.onInstalled.addListener(async () => {
  const cfg = await loadConfig();
  await saveConfig(cfg); // ensure defaults persisted

  registerContextMenus();
  initTabFeatures();
  initBurner();
  initSnapshot();
  initDNR();
});

// Keep service worker responsive to messages from side panel
chrome.runtime.onMessage.addListener((msg, sender, sendResponse) => {
  (async () => {
    const cfg = await loadConfig();

    if (msg?.type === "GET_CONFIG") return sendResponse({ ok: true, cfg });
    if (msg?.type === "SET_FEATURE") {
      cfg.features[msg.feature] = !!msg.enabled;
      await saveConfig(cfg);
      return sendResponse({ ok: true, cfg });
    }
    if (msg?.type === "DISCARD_TAB") {
      await chrome.tabs.discard(msg.tabId);
      return sendResponse({ ok: true });
    }
    if (msg?.type === "SNAPSHOT_TAB") {
      const res = await chrome.runtime.sendMessage({ type: "SNAPSHOT_NOW", tabId: msg.tabId });
      return sendResponse(res);
    }

    return sendResponse({ ok: false, error: "Unknown message" });
  })();

  return true; // async
});
